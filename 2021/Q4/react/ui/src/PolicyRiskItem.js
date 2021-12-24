import React from "react";

export class PolicyRiskItem extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      item: props.item
    }
  }

  handleItemChange = (e) => {
    this.state.item[e.target.name] = e.target.value;
    this.setState(this.state);
  }

  render() {
    return (
      <div style={{marginLeft: "1em", border: "1px #FFF solid"}}>
        <h3 style={{margin: "0em"}}>Item</h3>
        <input name="itemNo" value={this.state.item.itemNo} onChange={this.handleItemChange}/>
      </div>
    )
  }
}
