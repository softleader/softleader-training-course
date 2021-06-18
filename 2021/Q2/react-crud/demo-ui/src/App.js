import './App.css';
import React from "react";

export default class App extends React.Component {

  constructor() {
    super();
    this.state = {
      members: [],
      page: 0,
      totalPages: 0,
      newMember: {
        name: ""
      }
    };
  }

  handlePrevPage = () => {
    this.setState({page: --this.state.page}, this.fetchData);
  }

  handleNextPage = () => {
    this.setState({page: ++this.state.page}, this.fetchData);
  }

  handleSpecPage = (pageNum) => {
    this.setState({page: pageNum}, this.fetchData);
  }

  toEditMode = (id) => {
    let member = this.state.members.find(member => member.id === id);
    member.editMode = true;
    this.setState({members: this.state.members});
  }

  handleMemberChange = (e, member) => {
    member[e.target.name] = e.target.value;
    this.setState({members: this.state.members});
  }


  handleSave = (member) => {
    fetch("http://localhost:8080/member", {
      body: JSON.stringify(member), // must match 'Content-Type' header
      headers: {
        'content-type': 'application/json'
      },
      method: member.id ? 'PUT' : 'POST', // *GET, POST, PUT, DELETE, etc.
    })
      .then(this.fetchData)
  }

  handleDelete = (id) => {
    fetch(`http://localhost:8080/member/${id}`, {
      method: 'DELETE', // *GET, POST, PUT, DELETE, etc.
    })
      .then(this.fetchData);
  }

  fetchData = () => {
    fetch(`http://localhost:8080/member?page=${this.state.page}&size=10`)
      .then(response => response.json())
      .then(data => {
        this.setState({
          members: data.content,
          totalPages: data.totalPages
        })
      });
  }

  componentDidMount() {
    this.fetchData();
  }

  render() {
    return (
      <>
        <div>
          <span>
            <input name="name" onChange={(e) => this.handleMemberChange(e, this.state.newMember)} value={this.state.newMember.name}/>
          </span>
          <button onClick={() => this.handleSave(this.state.newMember)}>save</button>
        </div>
        <div>
          Member List
          {this.state.members.map(member => (
            <div>
              {member.editMode ? (
                <>
                  <button onClick={() => this.handleSave(member)}>save</button>
                  <span key={member.id}>
                    <input name="name" onChange={(e) => this.handleMemberChange(e, member)} value={member.name}/>
                    <input name="dutyDate" onChange={(e) => this.handleMemberChange(e, member)} value={member.dutyDate}/>
                  </span>
                </>
              ) : (
                <>
                  <button onClick={() => this.toEditMode(member.id)}>edit</button>
                  <button onClick={() => this.handleDelete(member.id)}>del</button>
                  <span key={member.id}>{member.name} {member.dutyDate}</span>
                </>
              )}
            </div>
          ))}
        </div>
        <div>
          <button type="button" onClick={this.handlePrevPage}>&lt;</button>
          {[...Array(this.state.totalPages).keys()].map(pageNum => (
            <button type="button" onClick={() => this.handleSpecPage(pageNum)}>{pageNum + 1}</button>
          ))}
          <button type="button" onClick={this.handleNextPage}>&gt;</button>
        </div>
        <div>{this.state.page + 1}/{this.state.totalPages}</div>
      </>
    )
  }
}