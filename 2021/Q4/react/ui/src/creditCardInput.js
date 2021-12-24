import React from "react";

export class CreditCardInput extends React.Component {

  constructor(props) {
    super(props);
    let splited = props.value?.split("-");
    this.state = {
      data1: splited?.length >= 1 && splited[0] || "",
      data2: splited?.length >= 1 && splited[1] || "",
      data3: splited?.length >= 1 && splited[2] || "",
      data4: splited?.length >= 1 && splited[3] || "",
    }
    this.inputRef.data1 = React.createRef();
    this.inputRef.data2 = React.createRef();
    this.inputRef.data3 = React.createRef();
    this.inputRef.data4 = React.createRef();
  }
  inputRef = {};

  handleChange = (e) => {
    let name = e.target.name;
    let changed = {
      [name]: e.target.value.substr(0, 4)
    };
    if (e.target.value.length >= 4) {
      if (name === "data1") {
        changed.data2 = "";
        this.inputRef.data2.current.focus();
      }
      if (name === "data2") {
        changed.data3 = "";
        this.inputRef.data3.current.focus();
      }
      if (name === "data3") {
        changed.data4 = "";
        this.inputRef.data4.current.focus();
      }
    }
    this.setState(changed, this.changedCallback);
  }

  changedCallback = () => {
    if (this.props.onChange) {
      this.props.onChange({
        target: {
          name: this.props.name,
          value: this.datasToOriValue()
        }
      })
    }
  }

  datasToOriValue = () => {
    return `${this.state.data1}-${this.state.data2}-${this.state.data3}-${this.state.data4}`
  }

  handleBackspace = (e) => {
    let name = e.target.name;
    if (e.keyCode === 8) {
      if (name === "data2" && this.state.data2.length <= 0) {
        this.inputRef.data1.current.focus();
      }
      if (name === "data3" && this.state.data3.length <= 0) {
        this.inputRef.data2.current.focus();
      }
      if (name === "data4" && this.state.data4.length <= 0) {
        this.inputRef.data3.current.focus();
      }
    }
  }

  render() {
    return (
      <div>
        <input ref={this.inputRef.data1} name="data1" style={{width: "3em"}} value={this.state.data1} onKeyDown={this.handleBackspace} onChange={this.handleChange}/>-
        <input ref={this.inputRef.data2} name="data2" style={{width: "3em"}} value={this.state.data2} onKeyDown={this.handleBackspace} onChange={this.handleChange}/>-
        <input ref={this.inputRef.data3} name="data3" style={{width: "3em"}} value={this.state.data3} onKeyDown={this.handleBackspace} onChange={this.handleChange}/>-
        <input ref={this.inputRef.data4} name="data4" style={{width: "3em"}} value={this.state.data4} onKeyDown={this.handleBackspace} onChange={this.handleChange}/>
      </div>
    )
  }
}
