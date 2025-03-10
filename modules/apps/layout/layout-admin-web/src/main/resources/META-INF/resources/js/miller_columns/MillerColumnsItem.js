/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import {ClayCheckbox} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import ClayLayout from '@clayui/layout';
import ClayLink from '@clayui/link';
import classNames from 'classnames';
import React, {useEffect, useMemo, useRef, useState} from 'react';
import {useDrag, useDrop} from 'react-dnd';
import {getEmptyImage} from 'react-dnd-html5-backend';

import {ACCEPTING_TYPES, ITEM_HOVER_BORDER_LIMIT} from './constants';

const DROP_ZONES = {
	BOTTOM: 'BOTTOM',
	ELEMENT: 'ELEMENT',
	TOP: 'TOP',
};

const ITEM_HOVER_TIMEOUT = 500;

const ITEM_STATES_COLORS = {
	'conversion-draft': 'info',
	draft: 'secondary',
	pending: 'info',
};

const isValidTarget = (sources, target, dropZone) => {
	if (sources.some((item) => item.id === target.id)) {
		return false;
	}

	if (
		sources.some(
			(source) =>
				!(
					(target.parentId &&
						target.columnIndex <= source.columnIndex) ||
					(target.columnIndex > source.columnIndex && !source.active)
				)
		)
	) {
		return false;
	}

	if (dropZone === DROP_ZONES.TOP) {
		return !sources.some(
			(source) =>
				!(
					target.columnIndex !== source.columnIndex ||
					target.itemIndex < source.itemIndex ||
					target.itemIndex > source.itemIndex + 1
				)
		);
	}
	else if (dropZone === DROP_ZONES.BOTTOM) {
		return !sources.some(
			(source) =>
				!(
					target.columnIndex !== source.columnIndex ||
					target.itemIndex > source.itemIndex ||
					target.itemIndex < source.itemIndex - 1
				)
		);
	}
	else if (dropZone === DROP_ZONES.ELEMENT) {
		return !sources.some(
			(source) => !(target.id !== source.parentId && target.parentable)
		);
	}
};

const getDropZone = (ref, monitor) => {
	if (!ref.current) {
		return;
	}

	const clientOffset = monitor.getClientOffset();
	const dropItemBoundingRect = ref.current.getBoundingClientRect();
	const hoverTopLimit = ITEM_HOVER_BORDER_LIMIT;
	const hoverBottomLimit =
		dropItemBoundingRect.height - ITEM_HOVER_BORDER_LIMIT;
	const hoverClientY = clientOffset.y - dropItemBoundingRect.top;

	let dropZone = DROP_ZONES.ELEMENT;

	if (hoverClientY < hoverTopLimit) {
		dropZone = DROP_ZONES.TOP;
	}
	else if (hoverClientY > hoverBottomLimit) {
		dropZone = DROP_ZONES.BOTTOM;
	}

	return dropZone;
};

const getItemIndex = (item = {}, items) => {
	const siblings = Array.from(items.values()).filter(
		(_item) => _item.columnIndex === item.columnIndex
	);

	return siblings.indexOf(item);
};

const noop = () => {};

const MillerColumnsItem = ({
	item: {
		actions = [],
		active,
		bulkActions = [],
		checked,
		columnIndex,
		description,
		draggable,
		hasChild,
		id: itemId,
		itemIndex,
		parentId,
		parentable,
		selectable,
		states = [],
		title,
		url,
		viewUrl,
	},
	items,
	actionHandlers = {},
	namespace,
	onItemDrop = noop,
	onItemStayHover = noop,
	rtl,
}) => {
	const ref = useRef();
	const timeoutRef = useRef();

	const [dropdownActionsActive, setDropdownActionsActive] = useState();
	const [dropZone, setDropZone] = useState();
	const [layoutActionsActive, setLayoutActionsActive] = useState();

	const dropdownActions = useMemo(() => {
		const dropdownActions = [];

		actions.forEach((action) => {
			if (!action.quickAction && !action.layoutAction) {
				const onClick = action.handler || actionHandlers[action.id];

				dropdownActions.push({
					...action,
					handler: () =>
						onClick && onClick({actionURL: action.url, namespace}),
					href: onClick ? null : action.url,
				});
			}
		});

		return dropdownActions;
	}, [actions, actionHandlers, namespace]);

	const layoutActions = useMemo(() => {
		return actions.filter((action) => action.layoutAction && action.url);
	}, [actions]);

	const quickActions = useMemo(() => {
		const quickActions = [];

		actions.forEach((action) => {
			if (action.quickAction && action.url) {
				quickActions.push({
					...action,
					handler:
						action.handler || actionHandlers[action.id] || noop,
				});
			}
		});

		return quickActions;
	}, [actions, actionHandlers]);

	const [{isDragging}, drag, previewRef] = useDrag({
		collect: (monitor) => ({
			isDragging: !!monitor.isDragging(),
		}),
		isDragging: (monitor) => {
			const movedItems = monitor.getItem().items;

			return (
				(movedItems.some((item) => item.checked) && checked) ||
				movedItems.some((item) => item.id === itemId)
			);
		},
		item: {
			items: checked
				? Array.from(items.values())
						.filter((item) => item.checked)
						.map((item) => ({
							...item,
							itemIndex: getItemIndex(item, items),
						}))
				: [
						{
							...items.get(itemId),
							itemIndex: getItemIndex(items.get(itemId), items),
						},
				  ],
			type: ACCEPTING_TYPES.ITEM,
		},
	});

	const [{isOver}, drop] = useDrop({
		accept: ACCEPTING_TYPES.ITEM,
		canDrop(source, monitor) {
			const dropZone = getDropZone(ref, monitor);

			return isValidTarget(
				source.items,
				{columnIndex, id: itemId, itemIndex, parentId, parentable},
				dropZone
			);
		},
		collect: (monitor) => ({
			isOver: !!monitor.isOver(),
		}),
		drop(source, monitor) {
			if (monitor.canDrop()) {
				if (dropZone === DROP_ZONES.ELEMENT) {
					const newIndex = Array.from(items.values()).filter(
						(item) => item.parentId === itemId
					).length;

					onItemDrop(source.items, itemId, newIndex);
				}
				else {
					let newIndex = itemIndex;

					if (dropZone === DROP_ZONES.BOTTOM) {
						newIndex = itemIndex + 1;
					}

					onItemDrop(source.items, parentId, newIndex);
				}
			}
		},
		hover(source, monitor) {
			let dropZone;

			if (isOver && monitor.canDrop()) {
				dropZone = getDropZone(ref, monitor);
			}

			setDropZone(dropZone);
		},
	});

	useEffect(() => {
		drag(drop(ref));
	}, [drag, drop]);

	useEffect(() => {
		previewRef(getEmptyImage(), {captureDraggingState: true});
	}, [previewRef]);

	useEffect(() => {
		if (!active && dropZone === DROP_ZONES.ELEMENT && !timeoutRef.current) {
			timeoutRef.current = setTimeout(() => {
				if (isOver) {
					onItemStayHover(itemId);
				}
			}, ITEM_HOVER_TIMEOUT);
		}
		else if (
			!isOver ||
			(dropZone !== DROP_ZONES.ELEMENT && timeoutRef.current)
		) {
			clearTimeout(timeoutRef.current);
			timeoutRef.current = null;
		}
	}, [active, dropZone, isOver, itemId, onItemStayHover]);

	return (
		<ClayLayout.ContentRow
			className={classNames('list-group-item-flex miller-columns-item', {
				dragging: isDragging,
				'drop-bottom': isOver && dropZone === DROP_ZONES.BOTTOM,
				'drop-element': isOver && dropZone === DROP_ZONES.ELEMENT,
				'drop-top': isOver && dropZone === DROP_ZONES.TOP,
				'miller-columns-item--active': active,
			})}
			containerElement="li"
			data-actions={bulkActions}
			ref={ref}
			verticalAlign="center"
		>
			<a className="miller-columns-item-mask" href={url}>
				<span className="c-inner sr-only">{`${Liferay.Language.get(
					'select'
				)} ${title}`}</span>
			</a>

			{draggable && (
				<ClayLayout.ContentCol className="miller-columns-item-drag-handler pl-0">
					<ClayIcon symbol="drag" />
				</ClayLayout.ContentCol>
			)}

			{selectable && (
				<ClayLayout.ContentCol>
					<ClayCheckbox
						defaultChecked={checked}
						name={`${namespace}rowIds`}
						value={itemId}
					/>
				</ClayLayout.ContentCol>
			)}

			<ClayLayout.ContentCol expand>
				<h4 className="list-group-title text-truncate-inline">
					<a className="text-truncate" href={viewUrl}>
						{title}
					</a>
				</h4>

				{description && (
					<h5 className="d-flex list-group-subtitle small">
						<span className="text-truncate">{description}</span>

						{states.map((state) => (
							<ClayLabel
								className="inline-item-after text-truncate"
								displayType={ITEM_STATES_COLORS[state.id]}
								key={state.id}
							>
								{state.label}
							</ClayLabel>
						))}
					</h5>
				)}
			</ClayLayout.ContentCol>

			{layoutActions.length > 0 && (
				<ClayLayout.ContentCol className="miller-columns-item-actions">
					<ClayDropDown
						active={layoutActionsActive}
						onActiveChange={setLayoutActionsActive}
						trigger={
							<ClayButtonWithIcon
								borderless
								displayType="secondary"
								small
								symbol="plus"
							/>
						}
					>
						<ClayDropDown.ItemList>
							{layoutActions.map((action) => (
								<ClayDropDown.Item
									disabled={!action.url}
									href={action.url}
									id={action.id}
									key={action.id}
									onClick={action.handler}
								>
									{action.label}
								</ClayDropDown.Item>
							))}
						</ClayDropDown.ItemList>
					</ClayDropDown>
				</ClayLayout.ContentCol>
			)}

			{quickActions.map((action) => (
				<ClayLayout.ContentCol
					className="miller-columns-item-quick-action"
					key={action.id}
				>
					<ClayLink
						borderless
						displayType="secondary"
						href={action.url}
						monospaced
						outline
					>
						<ClayIcon symbol={action.icon} />
					</ClayLink>
				</ClayLayout.ContentCol>
			))}

			{dropdownActions.length > 0 && (
				<ClayLayout.ContentCol className="miller-columns-item-actions">
					<ClayDropDown
						active={dropdownActionsActive}
						onActiveChange={setDropdownActionsActive}
						trigger={
							<ClayButtonWithIcon
								borderless
								displayType="secondary"
								small
								symbol="ellipsis-v"
							/>
						}
					>
						<ClayDropDown.ItemList>
							{dropdownActions.map((action) => (
								<ClayDropDown.Item
									disabled={!action.url}
									href={action.href}
									id={action.id}
									key={action.id}
									onClick={action.handler}
								>
									{action.label}
								</ClayDropDown.Item>
							))}
						</ClayDropDown.ItemList>
					</ClayDropDown>
				</ClayLayout.ContentCol>
			)}

			{hasChild && (
				<ClayLayout.ContentCol className="miller-columns-item-child-indicator">
					<ClayIcon symbol={rtl ? 'caret-left' : 'caret-right'} />
				</ClayLayout.ContentCol>
			)}
		</ClayLayout.ContentRow>
	);
};

export default MillerColumnsItem;
