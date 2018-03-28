import * as React from "react";

export default class ForOf extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    const array = ["a", "b", "c"];

    // before ES6
    let result1 = "";
    for(let n in array) {
      result1 += n;
    }

    // after ES6
    let result2 = "";
    for(let n of array) {
      result2 += n;
    }

    return `result1: ${result1}, result2: ${result2}`;
  }
}