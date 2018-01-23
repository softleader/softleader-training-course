import React from 'react';
import ReactDOM from 'react-dom';
import {applyMiddleware, combineReducers, createStore} from 'redux';
import {Provider} from 'react-redux';
import thunkMiddleware from 'redux-thunk';
import {createLogger} from 'redux-logger';
import {reducers} from './reducers/reducers';
import TodoList from "./containers/TodoList";

const store = createStore(
  combineReducers({
    ...reducers
  }),
  applyMiddleware(
    thunkMiddleware,
    createLogger())
);

window.app = {};

app.create = (dom) => {
  app.run(dom);
};

app.run = (dom) => {
  ReactDOM.render(
    <Provider store={store}>
      <TodoList/>
    </Provider>,
    dom
  )
};