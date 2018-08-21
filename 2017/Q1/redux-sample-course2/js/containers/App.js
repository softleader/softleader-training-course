import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import AddMessage from '../containers/AddMessage'
import MessageList from '../containers/MessageList'
import TagList from '../containers/TagList'
import { readMessage } from '../actions/actions'

class App extends React.Component {
  
  componentDidMount() {
    this.props.dispatch(readMessage(1)); 
  }

  render() {
    return (
      <div>
        <TagList />
        <hr />
        <MessageList />
        <br />
        <AddMessage />
      </div>
    )
  }

}

App.propTypes = {
  dispatch: PropTypes.func.isRequired
}

export default connect()(App);