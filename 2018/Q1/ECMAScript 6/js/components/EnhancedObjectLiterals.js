import * as React from "react";

export default class EnhancedObjectLiterals extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    const x = 1, y = 2, z = 3;

    // before ES6
    const obj1 = {x: x, y: y, z: z};

    // after ES6
    const obj2 = {x, y, z};

    return obj2.x + obj2.y + obj2.z;
  }
}