import * as React from "react";

export default class Destructuring extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    /**
     * list matching
     */
    // before ES6
    const a = 1, b = 2, c = 3;

    // after ES6
    const [d, e, f] = [1, 2, 3];
    const [x, , z] = [1, 2, 3];

    /**
     * object matching
     */
    const obj = {objA: "X", objB: "Y", objC: "Z"};

    // before ES6
    const obja = obj.objA, objb = obj.objB, objc = obj.objC;

    // after ES6
    const {objA, objB, objC} = obj;

    return `d: ${d}, e: ${e}, f: ${f}, objA: ${objA}, objB: ${objB}, objC: ${objC}`;
  }
}