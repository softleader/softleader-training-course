import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {createLogger} from 'redux-logger';
import { Provider } from "react-redux";
import * as serviceWorker from './serviceWorker';
import {createStore,applyMiddleware} from "redux";
import todoApp from "./reducers";
import stateShare from './middleware/stateShare';
import thunkMiddleware from 'redux-thunk';
let store = createStore(todoApp, applyMiddleware(
    stateShare,
    thunkMiddleware,
    createLogger()));
ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
