import React, { PropTypes } from 'react'
import Todo from './Todo'

export default class TodoList extends React.Component {

  render() {
    return (
      <ul>
        {this.props.todos.map(todo => 
          <Todo key={todo.id} {...todo} onClick={() => this.props.onTodoClick(todo.id)}></Todo>
        )}
      </ul>
    )
  }

}