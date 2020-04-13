/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import AddNote from '../../src/main/resources/META-INF/resources/js/components/AddNote';

function renderAddNote(props) {
	return render(<AddNote actionURL="add url" {...props} />);
}

function renderEditNote(props) {
	return render(
		<AddNote actionURL="edit url" content="test content" {...props} />
	);
}

describe('New Note', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAddNote();

		expect(container).toBeTruthy();
	});

	it('displays a textarea for adding a new note', () => {
		const {getByPlaceholderText} = renderAddNote();

		getByPlaceholderText('write-a-note');
	});

	it('displays a different textarea placeholder for Sales notes', () => {
		const {getByPlaceholderText} = renderAddNote({type: 'Sales'});

		getByPlaceholderText('write-sales-info');
	});

	it('prefills the textarea with original value for editing a note', () => {
		const {container} = renderEditNote();

		expect(container.querySelector('textarea').value).toMatch(
			'test content'
		);
	});

	it('displays a "Cancel" and a "Save" button when editing a note', () => {
		const {getByText} = renderEditNote();

		getByText('cancel');
		getByText('save');
	});

	it('restores the note content when the "Cancel" button is pressed', () => {
		const {container, getByText} = renderEditNote();
		const textarea = container.querySelector('textarea');

		fireEvent.change(textarea, {
			target: {value: 'new content'}
		});
		fireEvent.click(getByText('cancel'));

		expect(textarea.value).toMatch('test content');
	});
});
