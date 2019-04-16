/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.rest.client.json;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Rafael Praxedes
 * @generated
 */
@Generated("")
public abstract class BaseJSONParser<T> {

	public T parseToDTO(String json) {
		if (json == null) {
			throw new IllegalArgumentException("JSON is null");
		}

		_init(json);

		_assertStartsWithAndEndsWith("{", "}");

		T dto = createDTO();

		if (_isEmpty()) {
			return dto;
		}

		_readNextChar();

		_readWhileLastCharIsWhiteSpace();

		_readNextChar();

		if (_isLastChar('}')) {
			_readWhileLastCharIsWhiteSpace();

			if (!_isEndOfJSON()) {
				_readNextChar();

				throw new IllegalArgumentException(
					"Expected end of JSON, but found '" + _lastChar + "'");
			}

			return dto;
		}

		do {
			_readWhileLastCharIsWhiteSpace();

			String fieldName = _readValueAsString();

			_readWhileLastCharIsWhiteSpace();

			_assertLastChar(':');

			_readNextChar();

			_readWhileLastCharIsWhiteSpace();

			setField(dto, fieldName, _readValue());

			_readWhileLastCharIsWhiteSpace();
		}
		while (_ifLastCharMatchesThenRead(','));

		return dto;
	}

	public T[] parseToDTOs(String json) {
		if (json == null) {
			throw new IllegalArgumentException("JSON is null");
		}

		_init(json);

		_assertStartsWithAndEndsWith("[", "]");

		if (_isEmpty()) {
			return createDTOArray(0);
		}

		_readNextChar();

		_readWhileLastCharIsWhiteSpace();

		if (_isLastChar(']')) {
			_readNextChar();

			return createDTOArray(0);
		}

		_readWhileLastCharIsWhiteSpace();

		Object[] objects = (Object[])_readValue();

		return Stream.of(
			objects
		).map(
			object -> parseToDTO((String)object)
		).toArray(
			size -> createDTOArray(size)
		);
	}

	protected abstract T createDTO();

	protected abstract T[] createDTOArray(int size);

	protected abstract void setField(
		T dto, String jsonParserFieldName, Object jsonParserFieldValue);

	protected Date toDate(String string) {
		try {
			return _dateFormat.parse(string);
		}
		catch (ParseException pe) {
			throw new IllegalArgumentException(
				"Unable to parse date from " + string, pe);
		}
	}

	protected Date[] toDates(Object[] objects) {
		return Stream.of(
			objects
		).map(
			object -> {
				return toDate((String)object);
			}
		).toArray(
			size -> new Date[size]
		);
	}

	protected Integer[] toIntegers(Object[] objects) {
		return Stream.of(
			objects
		).map(
			object -> {
				try {
					return Integer.parseInt(object.toString());
				}
				catch (NumberFormatException nfe) {
					throw new RuntimeException(nfe);
				}
			}
		).toArray(
			size -> new Integer[size]
		);
	}

	protected Long[] toLongs(Object[] objects) {
		return Stream.of(
			objects
		).map(
			object -> {
				try {
					return Long.parseLong(object.toString());
				}
				catch (NumberFormatException nfe) {
					throw new RuntimeException(nfe);
				}
			}
		).toArray(
			size -> new Long[size]
		);
	}

	protected String toString(Date date) {
		return _dateFormat.format(date);
	}

	protected String[] toStrings(Object[] objects) {
		return Stream.of(
			objects
		).map(
			String.class::cast
		).toArray(
			size -> new String[size]
		);
	}

	private void _assertLastChar(char c) {
		if (_lastChar != c) {
			throw new IllegalArgumentException(
				String.format(
					"Expected last char '%s', but found '%s'", c, _lastChar));
		}
	}

	private void _assertStartsWithAndEndsWith(String prefix, String sufix) {
		if (!_json.startsWith(prefix)) {
			throw new IllegalArgumentException(
				String.format(
					"Expected starts with '%s', but found '%s'", prefix,
					_json.charAt(0)));
		}

		if (!_json.endsWith(sufix)) {
			throw new IllegalArgumentException(
				String.format(
					"Expected ends with '%s', but found '%s'", sufix,
					_json.charAt(_json.length() - 1)));
		}
	}

	private String _getCapturedSubstring() {
		return _json.substring(_captureStartStack.pop(), _index - 1);
	}

	private boolean _ifLastCharMatchesThenRead(char ch) {
		if (_lastChar != ch) {
			return false;
		}

		_readNextChar();

		return true;
	}

	private void _init(String json) {
		_captureStartStack = new Stack<>();
		_dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		_index = 0;
		_json = json.trim();
		_lastChar = 0;
	}

	private boolean _isEmpty() {
		String substring = _json.substring(1, _json.length() - 1);

		substring = substring.trim();

		return substring.isEmpty();
	}

	private boolean _isEndOfJSON() {
		if (_index == _json.length()) {
			return true;
		}

		return false;
	}

	private boolean _isLastChar(char c) {
		if (_lastChar == c) {
			return true;
		}

		return false;
	}

	private boolean _isLastCharDecimalSeparator() {
		if (_lastChar == '.') {
			return true;
		}

		return false;
	}

	private boolean _isLastCharDigit() {
		if ((_lastChar >= '0') && (_lastChar <= '9')) {
			return true;
		}

		return false;
	}

	private void _readNextChar() {
		if (!_isEndOfJSON()) {
			_lastChar = _json.charAt(_index++);
		}
	}

	private Object _readValue() {
		if (_lastChar == '[') {
			return _readValueAsArray();
		}
		else if (_lastChar == 'f') {
			return _readValueAsBooleanFalse();
		}
		else if (_lastChar == 't') {
			return _readValueAsBooleanTrue();
		}
		else if (_lastChar == 'n') {
			return _readValueAsObjectNull();
		}
		else if (_lastChar == '"') {
			return _readValueAsString();
		}
		else if (_lastChar == '{') {
			return _readValueAsStringJSON();
		}
		else if ((_lastChar == '-') || (_lastChar == '0') ||
				 (_lastChar == '1') || (_lastChar == '2') ||
				 (_lastChar == '3') || (_lastChar == '4') ||
				 (_lastChar == '5') || (_lastChar == '6') ||
				 (_lastChar == '7') || (_lastChar == '8') ||
				 (_lastChar == '9')) {

			return _readValueAsStringNumber();
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	private Object[] _readValueAsArray() {
		List<Object> objects = new ArrayList<>();

		_readNextChar();

		_readWhileLastCharIsWhiteSpace();

		if (_isLastChar(']')) {
			_readNextChar();

			return objects.toArray();
		}

		do {
			_readWhileLastCharIsWhiteSpace();

			objects.add(_readValue());

			_readWhileLastCharIsWhiteSpace();
		}
		while (_ifLastCharMatchesThenRead(','));

		if (!_isLastChar(']')) {
			throw new IllegalArgumentException(
				"Expected ']', but found '" + _lastChar + "'");
		}

		_readNextChar();

		return objects.toArray();
	}

	private boolean _readValueAsBooleanFalse() {
		_readNextChar();

		_assertLastChar('a');

		_readNextChar();

		_assertLastChar('l');

		_readNextChar();

		_assertLastChar('s');

		_readNextChar();

		_assertLastChar('e');

		_readNextChar();

		return false;
	}

	private boolean _readValueAsBooleanTrue() {
		_readNextChar();

		_assertLastChar('r');

		_readNextChar();

		_assertLastChar('u');

		_readNextChar();

		_assertLastChar('e');

		_readNextChar();

		return true;
	}

	private Object _readValueAsObjectNull() {
		_readNextChar();

		_assertLastChar('u');

		_readNextChar();

		_assertLastChar('l');

		_readNextChar();

		_assertLastChar('l');

		_readNextChar();

		return null;
	}

	private String _readValueAsString() {
		_readNextChar();

		_setCaptureStart();

		while (_lastChar != '"') {
			_readNextChar();
		}

		String string = _getCapturedSubstring();

		_readNextChar();

		return string;
	}

	private String _readValueAsStringJSON() {
		_setCaptureStart();

		_readNextChar();

		if (_isLastChar('}')) {
			_readNextChar();

			return _getCapturedSubstring();
		}

		_readWhileLastCharIsWhiteSpace();

		if (_isLastChar('}')) {
			_readNextChar();

			return _getCapturedSubstring();
		}

		do {
			_readWhileLastCharIsWhiteSpace();

			_readValueAsString();

			_readWhileLastCharIsWhiteSpace();

			if (!_ifLastCharMatchesThenRead(':')) {
				throw new IllegalArgumentException("Expected ':'");
			}

			_readWhileLastCharIsWhiteSpace();

			_readValue();

			_readWhileLastCharIsWhiteSpace();
		}
		while (_ifLastCharMatchesThenRead(','));

		_readWhileLastCharIsWhiteSpace();

		if (!_ifLastCharMatchesThenRead('}')) {
			throw new IllegalArgumentException(
				"Expected either ',' or '}', but found '" + _lastChar + "'");
		}

		return _getCapturedSubstring();
	}

	private String _readValueAsStringNumber() {
		_setCaptureStart();

		do {
			_readNextChar();
		}
		while (_isLastCharDigit() || _isLastCharDecimalSeparator());

		return _getCapturedSubstring();
	}

	private void _readWhileLastCharIsWhiteSpace() {
		while ((_lastChar == ' ') || (_lastChar == '\n') ||
			   (_lastChar == '\r') || (_lastChar == '\t')) {

			_readNextChar();
		}
	}

	private void _setCaptureStart() {
		_captureStartStack.push(_index - 1);
	}

	private Stack<Integer> _captureStartStack;
	private DateFormat _dateFormat;
	private int _index;
	private String _json;
	private char _lastChar;

}