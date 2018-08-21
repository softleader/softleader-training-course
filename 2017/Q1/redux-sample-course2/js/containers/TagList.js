import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import Message from '../components/Message'
import { readMessage } from '../actions/actions'

class TagList extends React.Component {

  render() {
    return (
      <div>
        <a href="#" onClick={() => {
            this.props.dispatch(readMessage(1));
          }}>Java</a>
        {" / "}
        <a href="#" onClick={() => {
            this.props.dispatch(readMessage(2));
          }}>React</a>
      </div>
    )
  }

}

TagList.propTypes = {
  dispatch: PropTypes.func.isRequired
}

export default connect()(TagList);