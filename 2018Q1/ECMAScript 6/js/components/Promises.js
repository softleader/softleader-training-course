import * as React from "react";

export default class Promises extends React.Component {

  constructor(props) {
    super(props);
    this.state = {msg: []};
  }

  componentDidMount() {
    function task1(react) {
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          react.setState({msg: [...react.state.msg, " task1 done"]});
          return resolve(" task1");
        }, 3000);
      });
    }

    function task2(react) {
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          react.setState({msg: [...react.state.msg, " task2 done"]});
          return reject(" task2");
        }, 1000);
      });
    }

    task1(this)
      .then(data => this.setState({msg: [...this.state.msg, data + " is success"]}));
    task2(this)
      .then(data => this.setState({msg: [...this.state.msg, data + " is success"]}))
      .catch(error => this.setState({msg: [...this.state.msg, error + " is error"]}));
  }

  render() {
    return this.state.msg;
  }
}