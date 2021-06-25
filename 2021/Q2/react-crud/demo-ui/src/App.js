import './App.css';
import React from "react";
import CrudDemo from "./page/crud";
import NestDemo from "./page/nest";

export default class App extends React.Component {

  constructor() {
    super();
    this.state = {
      page: "nest"
    };
  }

  handlePageChange = (e) => {
    this.setState({page: e.target.innerText});
  }

  render() {
    return (
      <>
        <button onClick={this.handlePageChange}>crud</button>
        <button onClick={this.handlePageChange}>nest</button>
        <hr/>
        {this.state.page === "crud" && <CrudDemo/>}
        {this.state.page === "nest" && <NestDemo/>}
      </>
    )
  }
}