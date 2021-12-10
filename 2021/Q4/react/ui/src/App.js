import React from "react";


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      queryResults: [],
      toInsertData: {
        name: "",
        role: "",
      }
    }
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
                <td><button type="button" onClick={() => this.handleDelete(data)}>del</button></td>
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
