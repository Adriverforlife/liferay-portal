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

import {createContext} from 'react';

const DEFAULT_STATE = {
	createExperimentModal: {active: false},
	editExperimentModal: {active: false},
	experiences: [],
	experiment: null,
	experimentHistory: [],
	selectedExperienceId: null,
	variants: []
};

export function getInitialState(firstState) {
	const {
		initialExperimentHistory,
		initialSegmentsExperiment,
		initialSegmentsVariants,
		initialSelectedSegmentsExperienceId,
		viewSegmentsExperimentDetailsURL,
		winnerSegmentsVariantId
	} = firstState;

	const state = {
		experiment: initialSegmentsExperiment,
		experimentHistory: initialExperimentHistory || [],
		selectedExperienceId: initialSelectedSegmentsExperienceId,
		variants: initialSegmentsVariants.map(initialVariant => {
			if (winnerSegmentsVariantId === initialVariant.segmentsExperienceId)
				return {...initialVariant, winner: true};
			return initialVariant;
		}),
		viewExperimentURL: viewSegmentsExperimentDetailsURL
	};

	return {
		...DEFAULT_STATE,
		...state
	};
}

export const DispatchContext = createContext();
export const StateContext = createContext(DEFAULT_STATE);
