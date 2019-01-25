let nextTodoId = 0
export const addTodo = (text) => {
    return {
        type: 'ADD_TODO',
        // id: nextTodoId++,
        text
    }
}
export const addTodoRobert = (text) => {
    return {
        type: 'ADD_TODO_ROBERT',
        // id: nextTodoId++,
        text
    }
}
export const removeTodo = (id) => {
    return {
        type: 'REMOVE_TODO',
        id
    }
}
export const addSeq = () => {
  return {
    type: 'ADD_SEQ'
  }

}


export const updateState = (text)=> {
  return function (dispatch) {
    return new Promise((resolve) => {
      dispatch(addSeq())
      resolve();

    })
    .then(() => {
        dispatch(addTodo(text))
    })

  }
}

