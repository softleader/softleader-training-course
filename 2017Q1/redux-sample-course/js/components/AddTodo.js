import React from 'react'

export default class AddTodo extends React.Component {

  render() {
    let input;

    return (
      <div>
        <form onSubmit={e => {
          e.preventDefault();
          if(!input.value.trim()) {
            return;
          }
          this.props.addTodo(input.value);
          input.value = '';
          }}>

          <input type="text" ref={ref => input = ref} />
          <button type="submit">Add Todo</button>
        </form>
      </div>
    )
  }

}