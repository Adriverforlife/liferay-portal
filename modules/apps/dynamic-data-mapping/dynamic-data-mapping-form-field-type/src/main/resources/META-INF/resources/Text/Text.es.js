import '../FieldBase/FieldBase.es';
import './TextRegister.soy.js';
import 'clay-autocomplete';
import Component from 'metal-component';
import Soy from 'metal-soy';
import templates from './Text.soy.js';
import {Config} from 'metal-state';

class Text extends Component {
	dispatchEvent(event, name, value) {
		this.emit(name, {
			fieldInstance: this,
			originalEvent: event,
			value
		});
	}

	getAutocompleteOptions() {
		const {options} = this;

		if (!options) {
			return [];
		}

		return options.map(option => {
			return option.label;
		});
	}

	prepareStateForRender(state) {
		const {options} = this;

		return {
			...state,
			options: this.getAutocompleteOptions(options)
		};
	}

	willReceiveState(changes) {
		if (changes.value) {
			this.setState({
				_value: changes.value.newVal
			});
		}
	}

	_handleAutocompleteFieldChanged(event) {
		const {value} = event.data;

		this.setState(
			{
				value
			},
			() => this.dispatchEvent(event, 'fieldEdited', value)
		);
	}

	_handleAutocompleteFieldFocused(event) {
		this.dispatchEvent('fieldFocused', event, event.target.inputValue);
	}

	_handleAutocompleteFilteredItemsChanged(filteredItemsReceived) {
		const {filteredItems} = this;

		if (filteredItemsReceived.newVal.length != filteredItems.length) {
			this.setState({
				filteredItems: filteredItemsReceived.newVal
			});
		}
	}

	_handleAutocompleteSelected(event) {
		const {value} = event.data.item;

		this.setState(
			{
				value,
				filteredItems: []
			},
			() => {
				this.dispatchEvent(event, 'fieldEdited', value);
			}
		);
	}

	_handleFieldBlurred(event) {
		this.dispatchEvent(event, 'fieldBlurred', event.target.value);
	}

	_handleFieldChanged(event) {
		const {value} = event.target;

		this.setState(
			{
				value
			},
			() => this.dispatchEvent(event, 'fieldEdited', value)
		);
	}

	_handleFieldFocused(event) {
		this.dispatchEvent(event, 'fieldFocused', event.target.value);
	}

	_internalValueFn() {
		const {value} = this;

		return value;
	}
}

Text.STATE = {
	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	_value: Config.string()
		.internal()
		.valueFn('_internalValueFn'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	autocompleteEnabled: Config.bool(),

	/**
	 * @default 'string'
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	dataType: Config.string().value('string'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	displayStyle: Config.string().value('singleline'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	errorMessage: Config.string(),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?bool}
	 */

	evaluable: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	fieldName: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	filteredItems: Config.array()
		.value([])
		.internal(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	name: Config.string().required(),

	/**
	 * @default []
	 * @memberof Text
	 * @type {?array<object>}
	.setter('_loadOptionsFn').
	 */

	options: Config.arrayOf(
		Config.shapeOf({
			active: Config.bool().value(false),
			disabled: Config.bool().value(false),
			id: Config.string(),
			inline: Config.bool().value(false),
			label: Config.string(),
			name: Config.string(),
			showLabel: Config.bool().value(true),
			value: Config.string()
		})
	).value([]),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	placeholder: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	predefinedValue: Config.string().value(''),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?bool}
	 */

	readOnly: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldBase
	 * @type {?(bool|undefined)}
	 */

	repeatable: Config.bool(),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?(bool|undefined)}
	 */

	required: Config.bool().value(false),

	/**
	 * @default true
	 * @instance
	 * @memberof Text
	 * @type {?(bool|undefined)}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	tooltip: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	type: Config.string().value('text'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	value: Config.string().value('')
};

Soy.register(Text, templates);

export default Text;
