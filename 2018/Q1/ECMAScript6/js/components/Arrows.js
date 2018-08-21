import * as React from "react";

export default class Arrows extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    let array = [0, 1, 2];

    // before ES6
    array = array.map(function (e) {
      return e + 1;
    });

    // after ES6
    array = array.map(e => e + 1);

    return array;
  }
}