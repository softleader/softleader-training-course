import React from 'react'
import VisibleAddTodo from '../containers/VisibleAddTodo'
import VisibleTodoList from '../containers/VisibleTodoList'

export default class App extends React.Component {

  render() {
    return (
      <div>
        <VisibleAddTodo />
        <VisibleTodoList />
      </div>
    )
  }

}