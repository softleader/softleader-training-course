import React from "react";
import {PolicyRisk} from "./PolicyRisk";

export class Policy extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      policy: props.policy
    }
  }

  handlePolicyChange = (e) => {
    this.state.policy[e.target.name] = e.target.value;
    this.setState(this.state);
  }

  handleAddRisk = (e) => {
    this.state.policy.risks.push({riskId: "請輸入", items: []});
    this.setState(this.state);
  }

  render() {
    return (
      <div style={{marginLeft: "1em", border: "1px #FFF solid"}}>
        <h3 style={{margin: "0em"}}>Policy</h3>
        <input name="policyNo" value={this.state.policy.policyNo} onChange={this.handlePolicyChange}/>
        <hr/>
        <button type="button" onClick={this.handleAddRisk}>+Risk</button>
        {this.state.policy.risks?.map(risk => (
          <PolicyRisk risk={risk}/>
        ))}
      </div>
    )
  }
}
