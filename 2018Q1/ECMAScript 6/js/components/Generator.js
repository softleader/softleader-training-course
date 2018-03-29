import * as React from "react";

export default class Generator extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    function* hello() {
      yield 'hello';
      yield 'world';
    }

    const iter = hello();

    // object For..Of
    const obj = {a: 1, b: 2, c: 3};

    return JSON.stringify(iter.next()) + ", " + JSON.stringify(iter.next()) + ", " + JSON.stringify(iter.next());
  }
}