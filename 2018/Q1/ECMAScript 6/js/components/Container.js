import * as React from "react";
import Arrows from "./Arrows";
import Children from "./classes/Children";
import EnhancedObjectLiterals from "./EnhancedObjectLiterals";
import TemplateStrings from "./TemplateStrings";
import DefaultParameter from "./DefaultParameter";
import RestSpreadParameter from "./RestSpreadParameter";
import LetConst from "./LetConst";
import es6BindAll from "es6bindall";
import ForOf from "./ForOf";
import Generator from "./Generator";
import SetMap from "./SetMap";
import Promises from "./Promises";
import Destructuring from "./Destructuring";
import ProxySample from "./ProxySample";

export default class Container extends React.Component {

  constructor(props) {
    super(props);
    this.state = {component: ""};
    es6BindAll(this, "handleClick");
  }

  handleClick(component) {
    this.setState({component});
  }

  render() {
    const [main, left, right] =
      [{overflow: "hidden", width:"100%"}, {float:"left", width:"30%"},
        {position: "fixed", width:"70%", top: "30%", left: "40%"}];

    return (
      <div style={main}>
        <div style={left}>
          <p>
            <button onClick={() => this.handleClick(
              <div>
                non static: getX: {new Children().getX}, getY: {new Children().getY}
                <br/>
                static: {Children.getZ()}
              </div>
            )}>Classes</button>
          </p>

          <p><button onClick={() => this.handleClick(<LetConst/>)}>Let Const</button></p>

          <p><button onClick={() => this.handleClick(<TemplateStrings/>)}>Template Strings</button></p>

          <p><button onClick={() => this.handleClick(<Arrows/>)}>Arrows</button></p>

          <p><button onClick={() => this.handleClick(<EnhancedObjectLiterals/>)}>Enhanced Object Literals</button></p>

          <p><button onClick={() => this.handleClick(<Destructuring/>)}>Destructuring</button></p>

          <p><button onClick={() => this.handleClick(<DefaultParameter/>)}>Default Parameter</button></p>

          <p><button onClick={() => this.handleClick(<RestSpreadParameter/>)}>Rest Spread Parameter</button></p>

          <p><button onClick={() => this.handleClick(<ForOf/>)}>For..Of</button></p>

          <p><button onClick={() => this.handleClick(<SetMap/>)}>Set Map</button></p>

          <p><button onClick={() => this.handleClick(<Generator/>)}>Generator</button></p>

          <p><button onClick={() => this.handleClick(<Promises/>)}>Promises</button></p>

          <p><button onClick={() => this.handleClick(<ProxySample/>)}>Proxy</button></p>
        </div>

        <div style={right}>
          {this.state.component}
        </div>
      </div>
    )
  }
}