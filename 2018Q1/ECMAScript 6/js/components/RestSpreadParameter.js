import * as React from "react";

export default class RestSpreadParameter extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    /**
     * rest parameter
     */
    function f(x, ...y) {
      // y is an Array
      return x * y.length;
    }

    /**
     * spread parameter
     */
    const array1 = [1, 2, 3];
    const array2 = [...array1, 4, 5, 6];

    const obj1 = {a: 1, b: 2, c: 3};
    const obj2 = {...obj1, x: 4, y: 5, z: 6};

    return `f(3, \"hello\", true) === ${f(3, "hello", true)}, array2 === ${array2}`;
  }
}