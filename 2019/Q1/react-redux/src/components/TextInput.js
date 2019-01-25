import React, {Component} from 'react';

class TextInput extends Component {

	render() {
		return (
			<input type='text'
				   value={this.props.value}
				   placeholder='input'
				   onChange={this.props.onChange}
			/>
		);
	}

}

export default TextInput;
