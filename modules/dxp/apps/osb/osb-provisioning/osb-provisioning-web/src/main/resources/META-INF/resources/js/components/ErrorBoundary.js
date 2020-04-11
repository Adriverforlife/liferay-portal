import React from 'react';
import PropTypes from 'prop-types';

export default class ErrorBoundary extends React.Component {
	state = {
		error: '',
		hasError: false,
		info: ''
	};

	static propTypes = {
		children: PropTypes.any
	};

	componentDidCatch(error, info) {
		this.setState({
			error,
			hasError: true,
			info
		});

		if (process.env.NODE_ENV === 'development') {
			console.log(`Error: ${error}`);
			console.log(`Error Info: ${JSON.stringify(info)}`);
		}
	}

	render() {
		return this.state.hasError ? (
			<div className="alert alert-danger" role="alert">
				{Liferay.Language.get('your-component-failed-to-render')}
			</div>
		) : (
			this.props.children
		);
	}
}
