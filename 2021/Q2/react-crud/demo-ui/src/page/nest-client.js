import '../App.css';
import React from "react";
import PropTypes from 'prop-types';

export default class Client extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      client: props.client
    };
  }

  handleClientChange = (e) => {
    this.state.client[e.target.name] = e.target.value;
    this.setState({client: this.state.client})
    this.props.onChange();
  }

  render() {
    return (
      <div>
        <div>
          [{this.state.client.type}]
          Name: {this.state.client.fullName}
        </div>
        <div>
          <label>電話號碼</label>
          <input name="tel" onChange={this.handleClientChange} value={this.state.client.tel}/>
        </div>
        <div>
          <label>電子信箱</label>
          <input name="email" onChange={this.handleClientChange} value={this.state.client.email}/>
        </div>
      </div>
    )
  }
}