import * as React from "react";

export default class SetMap extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    // Set
    const s = new Set();
    s.add("hello").add("word").add("david");

    // Map
    const m = new Map();
    m.set("hello", 42);
    m.set(s, 34);

    return `Set size: ${s.size}, has hello: ${s.has("hello")}, Map size: ${m.size}, s = ${m.get(s)}`;
  }
}