import React, {Component} from 'react';

class TextShow extends Component {

	render() {
		return !!this.props.values && this.props.values.map(value => {
			return (
				<p key={value.id}
				   onClick={e => this.props.onClick(e, value.id)}>
					{value.id}: {value.value}
				</p>
			);
		});
	}

}

export default TextShow;
