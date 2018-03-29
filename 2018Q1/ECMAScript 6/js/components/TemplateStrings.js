import * as React from "react";

export default class TemplateStrings extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    const lastName = "David", firstName = "Hsu";

    return `Hello, ${lastName} ${firstName}`;
  }
}