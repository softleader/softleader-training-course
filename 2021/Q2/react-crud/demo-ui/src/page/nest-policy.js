import '../App.css';
import React from "react";
import PropTypes from 'prop-types';
import Client from "./nest-client";

export default class Policy extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      policy: props.policy
    };
  }

  handlePolicyChange = (e) => {
    this.state.policy[e.target.name] = e.target.value;
    this.setState({policy: this.state.policy})
    this.props.onChange();
  }

  render() {
    return (
      <div className="row">
        <div>
          <label>保單號碼</label>
          <input name="policyNo" onChange={this.handlePolicyChange} value={this.state.policy.policyNo}/>
        </div>
        {this.state.policy.clients.map(client => (<Client onChange={this.props.onChange} client={client}/>))}
      </div>
    )
  }
}