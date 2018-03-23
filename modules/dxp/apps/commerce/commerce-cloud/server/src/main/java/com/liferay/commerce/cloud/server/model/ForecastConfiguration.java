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

package com.liferay.commerce.cloud.server.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.Set;

/**
 * @author Andrea Di Giorgi
 */
@DataObject(generateConverter = true, publicConverter = false)
public class ForecastConfiguration implements Jsonable {

	public ForecastConfiguration() {
	}

	public ForecastConfiguration(JsonObject jsonObject) {
		ForecastConfigurationConverter.fromJson(jsonObject, this);
	}

	public int getAhead() {
		return _ahead;
	}

	public Frequency getFrequency() {
		return _frequency;
	}

	public Set<Level> getLevels() {
		return _levels;
	}

	public Set<Period> getPeriods() {
		return _periods;
	}

	public Set<Target> getTargets() {
		return _targets;
	}

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	public void setAhead(int ahead) {
		_ahead = ahead;
	}

	public void setFrequency(Frequency frequency) {
		_frequency = frequency;
	}

	public void setLevels(Set<Level> levels) {
		_levels = levels;
	}

	public void setPeriods(Set<Period> periods) {
		_periods = periods;
	}

	public void setTargets(Set<Target> targets) {
		_targets = targets;
	}

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;
	}

	@Override
	public JsonObject toJson() {
		JsonObject jsonObject = new JsonObject();

		ForecastConfigurationConverter.toJson(this, jsonObject);

		return jsonObject;
	}

	public enum Frequency {

		DAILY, MONTHLY, WEEKLY

	}

	public enum Level {

		COMPANY, CUSTOMER, CUSTOMER_SKU, SKU

	}

	public enum Period {

		MONTHLY, WEEKLY

	}

	public enum Target {

		QUANTITY, REVENUE

	}

	private int _ahead;
	private Frequency _frequency;
	private Set<Level> _levels;
	private Set<Period> _periods;
	private Set<Target> _targets;
	private String _timeZoneId;

}