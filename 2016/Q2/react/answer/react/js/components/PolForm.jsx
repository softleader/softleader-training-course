import React from "react";
import PolStore from "../stores/PolStore.jsx";
import MailStore from "../stores/MailStore.jsx";
import SmsStore from "../stores/SmsStore.jsx";
import PolAction from "../actions/PolAction.jsx";
import es6BindAll from "es6bindall";

export default class PolForm extends React.Component {

  constructor(props) {
    super(props);
    this.state = {};
    
    // set input default value
    this.inputCount = 2;
    for(let i=0; i<this.inputCount; i++) {
      let id = i + 1;
      this.state["name" + id] = "1";
    }

    es6BindAll(this, [
      "_onChange", "handleInputChange", "handleSubmit"
    ]);
  }

  componentDidMount() {
    PolStore.addChangeListener(this._onChange);
  }

  componentWillUnmount() {
    PolStore.removeChangeListener(this._onChange);
  }

  _onChange() {
    this.setState(PolStore.getDatas().formData);
  }

  handleInputChange(e) {
    var obj = {};
    obj[e.target.name] = e.target.value;
    this.setState(obj);
  }

  handleSubmit() {
    PolAction.handleSubmit(this.state);
  }

  render() {

    return (
      <form>
        <input type="button" defaultValue="Submit" onClick={this.handleSubmit} /> <br />
        <input type="text" name="name1" value={this.state.name1} onChange={this.handleInputChange} /> <br />
        <input type="text" name="name2" value={this.state.name2} onChange={this.handleInputChange} /> <br />
      </form>
    );
  }

}