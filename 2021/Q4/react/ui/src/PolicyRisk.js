import React from "react";
import {PolicyRiskItem} from "./PolicyRiskItem";

export class PolicyRisk extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      risk: props.risk
    }
  }

  handleRiskChange = (e) => {
    this.state.risk[e.target.name] = e.target.value;
    this.setState(this.state);
  }

  handleAddItem = (e) => {
    this.state.risk.items.push({itemNo: "請輸入"});
    this.setState(this.state);
  }

  render() {
    return (
      <div style={{marginLeft: "1em", border: "1px #FFF solid"}}>
        <h3 style={{margin: "0em"}}>Risk</h3>
        <input name="riskId" value={this.state.risk.riskId} onChange={this.handleRiskChange}/>
        <hr/>
        <button type="button" onClick={this.handleAddItem}>+Item</button>
        {this.state.risk.items.map(item => (
          <PolicyRiskItem item={item}/>
        ))}
      </div>
    )
  }
}
