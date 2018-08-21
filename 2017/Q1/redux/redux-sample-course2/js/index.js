import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { createStore, applyMiddleware } from 'redux'
import thunkMiddleware from 'redux-thunk'
import { createLogger } from 'redux-logger'
import rootReducer from './reducers/reducers'
import App from './containers/App'
import performanceMonitor from './middlewares/performanceMonitor'

let store = createStore(rootReducer, 
  applyMiddleware(
    thunkMiddleware,
    createLogger(),
    performanceMonitor
  ));

render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
)