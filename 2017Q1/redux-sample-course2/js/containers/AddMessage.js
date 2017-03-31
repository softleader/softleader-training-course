import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import { addMessage } from '../actions/actions'

class AddMessage extends React.Component {

  render() {
    let input;

    return (
      <div>
        <form onSubmit={e => {
            e.preventDefault();
            if(!input.value.trim()) {
              return;
            }
            this.props.dispatch(addMessage(this.props.rootId, input.value));
            input.value = '';
          }}>

          <input type="text" ref={ref => input = ref} />
          <button type="submit">Add Message</button>
        </form>
      </div>
    )
  }

}

AddMessage.propTypes = {
  dispatch: PropTypes.func.isRequired
}

const mapStateToProps = (state) => {
  return {
    rootId: !!state.messages[0] ? state.messages[0].rootId : undefined
  }
}

export default connect(mapStateToProps)(AddMessage);