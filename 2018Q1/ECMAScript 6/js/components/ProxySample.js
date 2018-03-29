import * as React from "react";

export default class ProxySample extends React.Component {

  constructor(props) {
    super(props);
  }

  componentDidMount() {
    softleaderDocument.softleader;
    alert(softleaderDocument.location.href);
  }

  render() {
    window.softleaderDocument = new Proxy(document, {
      get: function(target, key) {
        if(key === "softleader") {
          alert ("Hello");
          return;
        }
        return target[key];
      }
    });

    return null;
  }
}