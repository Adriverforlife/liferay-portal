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

package com.liferay.commerce.cloud.server.eleflow.service.impl;

import com.liferay.commerce.cloud.server.eleflow.model.EleflowForecastScheduler;
import com.liferay.commerce.cloud.server.eleflow.util.EleflowForecastSchedulerDecoder;
import com.liferay.commerce.cloud.server.eleflow.util.EleflowForecastSchedulerEncoder;
import com.liferay.commerce.cloud.server.model.ForecastConfiguration;
import com.liferay.commerce.cloud.server.model.Project;
import com.liferay.commerce.cloud.server.service.ForecastConfigurationService;
import com.liferay.commerce.cloud.server.util.VertxUtil;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.codec.impl.BodyCodecImpl;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Andrea Di Giorgi
 */
public class EleflowForecastConfigurationServiceImpl
	implements ForecastConfigurationService {

	public EleflowForecastConfigurationServiceImpl(
		Vertx vertx, String callbackURL, String eleflowHost,
		String eleflowPath) {

		_callbackURL = Objects.requireNonNull(callbackURL);
		_eleflowPath = Objects.requireNonNull(eleflowPath);

		WebClientOptions webClientOptions = new WebClientOptions();

		webClientOptions.setDefaultHost(Objects.requireNonNull(eleflowHost));
		webClientOptions.setDefaultPort(443);
		webClientOptions.setSsl(true);

		_webClient = WebClient.create(vertx, webClientOptions);
	}

	@Override
	public void getForecastConfiguration(
		Project project, Handler<AsyncResult<ForecastConfiguration>> handler) {

		HttpRequest<ForecastConfiguration> httpRequest = _webClient.get(
			_eleflowPath + "/scheduler"
		).as(
			_bodyCodec
		);

		_addAuthorization(httpRequest, project);

		httpRequest.send(
			asyncResult -> VertxUtil.handleServiceHttpResponse(
				asyncResult, handler));
	}

	@Override
	public void updateForecastConfiguration(
		Project project, ForecastConfiguration forecastConfiguration,
		Handler<AsyncResult<Void>> handler) {

		HttpRequest<Void> httpRequest = _webClient.put(
			_eleflowPath + "/scheduler"
		).as(
			BodyCodec.none()
		);

		_addAuthorization(httpRequest, project);

		String callbackURL = _callbackURL.replace(
			":projectId", project.getId());

		EleflowForecastScheduler eleflowForecastScheduler =
			_eleflowForecastSchedulerEncoder.apply(
				forecastConfiguration, callbackURL);

		httpRequest.sendJson(
			eleflowForecastScheduler,
			asyncResult -> VertxUtil.handleServiceHttpResponse(
				asyncResult, handler));
	}

	private void _addAuthorization(
		HttpRequest<?> httpRequest, Project project) {

		httpRequest.putHeader("x-api-key", project.getApiKey());
	}

	private static final BodyCodec<ForecastConfiguration> _bodyCodec;
	private static final
		BiFunction<ForecastConfiguration, String, EleflowForecastScheduler>
			_eleflowForecastSchedulerEncoder =
				new EleflowForecastSchedulerEncoder();

	static {
		Function<Buffer, ForecastConfiguration> function =
			BodyCodecImpl.jsonDecoder(
				EleflowForecastScheduler.class
			).andThen(
				new EleflowForecastSchedulerDecoder()
			);

		_bodyCodec = BodyCodec.create(function);
	}

	private final String _callbackURL;
	private final String _eleflowPath;
	private final WebClient _webClient;

}