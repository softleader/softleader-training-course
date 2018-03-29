import * as React from "react";

export default class DefaultParameter extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    // before ES6
    function abc(x) {
      if(!!x) {
        x = 100;
      }
      return x;
    }

    // after ES6
    function xyz(x = 100) {
      return x;
    }

    return xyz();
  }
}