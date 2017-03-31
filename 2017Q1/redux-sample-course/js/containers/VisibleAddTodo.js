import React from 'react'
import { connect } from 'react-redux'
import { addTodo } from '../actions/actions'
import AddTodo from '../components/AddTodo'

const mapStateToProps = (state) => {
  return {}
}

const mapDispatchToProps = (dispatch) => {
  return {
    addTodo: (text) => {
      dispatch(addTodo(text));
    }
  }
}

const VisibleAddTodo = connect(
  mapStateToProps,
  mapDispatchToProps
)(AddTodo)

export default VisibleAddTodo;