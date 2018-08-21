import React from 'react';
import ReactDOM from 'react-dom';
import Container from "./components/Container";

window.app = {};

app.create = (dom) => {
  app.run(dom);
};

app.run = (dom) => {
  ReactDOM.render(
    <Container/>,
    dom
  )
};