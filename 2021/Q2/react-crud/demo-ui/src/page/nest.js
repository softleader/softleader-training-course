import '../App.css';
import React from "react";
import Policy from "./nest-policy";

export default class NestDemo extends React.Component {

  constructor() {
    super();
    this.state = {
      policy: {
        policyNo: "SL021Q2TPE30324042",
        effYear: "2021",
        acceptTime: "2021-06-25T10:45:33.221",
        clients: [
          {idno: "A123456789", fullName: "Rhys Chang", type: "要保人", seqNo: 1, tel: "0987654321", email: "rhys.chang@softleader.com.tw"},
          {idno: "A123456789", fullName: "Rhys Chang", type: "被保人", seqNo: 2, tel: "0987654321", email: "rhys.chang@softleader.com.tw"},
        ]
      }
    };
  }

  render() {
    return (
      <div className="row">
        <div className="column">
          <button onClick={e => {console.log(JSON.stringify(this.state.policy,null,2))}}>save</button>
          <Policy onChange={() => this.setState({})} policy={this.state.policy}/>
        </div>
        <div className="column" style={{backgroundColor: "#EEEEEE"}}>
          <button onClick={() => this.setState({})}>force fresh</button>
          <pre>{JSON.stringify(this.state.policy,null,2)}</pre>
        </div>
      </div>
    )
  }
}