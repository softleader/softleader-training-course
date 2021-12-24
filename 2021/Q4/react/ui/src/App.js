import React from "react";
import {CreditCardInput} from "./creditCardInput";
import {CreditInput} from "./functionComponents";
import {Policy} from "./Policy";


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      credit: "1234-5678-9012-3456",
      memValue: "",
      queryResults: [],
      toInsertData: {
        name: "",
        role: "",
      },
      policy: {
        policyNo: "SL202112041111",
        risks: [
          {
            riskId: "路人甲", items: [{itemNo: "IT001"}, {itemNo: "IT002"}]
          },
          {
            riskId: "路人乙", items: [{itemNo: "IT301"}, {itemNo: "IT302"}, {itemNo: "IT303"}]
          }
        ]
      },
      showPolicy: ""
    }
  }

  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    });
  }

  handleChangeInsertData = (e) => {
    this.setState({
      toInsertData: {
        ...this.state.toInsertData,
        [e.target.name]: e.target.value
      }
    });
  }

  handleQuery = () => {
    let self = this;
    fetch("http://localhost:8080/member", {
      method: 'GET',
    }).then(response => response.json())
      .then(data => {
        self.setState({queryResults: data})
      })
  }

  handleInsert = () => {
    fetch("http://localhost:8080/member", {
      body: JSON.stringify(this.state.toInsertData),
      headers: {'content-type': 'application/json'},
      method: 'POST',
    }).then(() => this.handleQuery());
  }

  handleDelete = (data) => {
    fetch(`http://localhost:8080/member/${data.id}`, {
      method: 'DELETE',
    }).then(() => this.handleQuery());
  }

  render() {
    return (
      <div className="App">
        <h1>sample</h1>

        <div>
          <CreditCardInput name="credit" value={this.state.credit} onChange={this.handleChange}/>
        </div>

        <hr/>
        <CreditInput value={this.state.credit}/>
        <hr/>

        <hr/>

        <Policy policy={this.state.policy}/>

        <button type="button" onClick={() => this.setState({showPolicy: JSON.stringify(this.state.policy)})}>Save</button>

        <code style={{backgroundColor: "#CCC"}}>{this.state.showPolicy}</code>

        <div>
          <h1>{this.props.fnName}</h1>

          <label>名字</label>
          <input type="text" name="name" onChange={this.handleChangeInsertData} value={this.state.toInsertData.name}/>
          <input type="text" name="role" onChange={this.handleChangeInsertData} value={this.state.toInsertData.role}/>
          <button type="button" onClick={this.handleInsert}>insert</button>
        </div>

        <hr/>

        <div>
          <button type="button" onClick={this.handleQuery}>query</button>
          <table>
            <thead>
            <tr>
              <th>action</th>
              <th>id</th>
              <th>name</th>
              <th>role</th>
            </tr>
            </thead>
            <tbody>
            {this.state.queryResults.map(data => (
              <tr key={data.id}>
                <td>
                  <button type="button" onClick={() => this.handleDelete(data)}>del</button>
                </td>
                <td>{data.id}</td>
                <td>{data.name}</td>
                <td>{data.role}</td>
              </tr>
            ))}
            </tbody>
          </table>
        </div>
      </div>
    )
  }
}

export default App;
