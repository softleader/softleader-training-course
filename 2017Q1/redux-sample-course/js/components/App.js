import React from 'react'
import AddTodo from '../containers/AddTodo'
import VisibleTodoList from '../containers/VisibleTodoList'

export default class App extends React.Component {

  render() {
    return (
      <div>
        <AddTodo />
        <VisibleTodoList />
      </div>
    )
  }

}