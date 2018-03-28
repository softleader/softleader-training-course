import * as React from "react";

export default class LetConst extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    // before ES6
    function useVar() {
      for(var i = 0; i<10; i++) {

      }
      try {
        return i;
      } catch(e) {
        return "error in useVar"
      }
    }

    // after ES6
    function useLet() {
      for(let i = 0; i<10; i++) {

      }
      try {
        return i;
      } catch(e) {
        return "error in useLet"
      }
    }

    return `useVar: ${useVar()}, useLet: ${useLet()}`;
  }
}