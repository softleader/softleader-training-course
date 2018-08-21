import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import Message from '../components/Message'
import { openEditPanel, deleteMessage } from '../actions/actions'

class MessageList extends React.Component {

  render() {
    return (
      <div>
        {this.props.messages.map(message => 
          <Message key={message.id} {...message} 
            openEditPanel={() => this.props.dispatch(openEditPanel(message.id))}
            deleteMessage={() => this.props.dispatch(deleteMessage(message.rootId, message.id))}
          ></Message>
        )}
      </div>
    )
  }

}

MessageList.propTypes = {
  dispatch: PropTypes.func.isRequired
}

const mapStateToProps = (state) => {
  return {
    messages: state.messages
  }
}

export default connect(mapStateToProps)(MessageList);