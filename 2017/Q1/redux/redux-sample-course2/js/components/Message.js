import React, { PropTypes } from 'react'

export default class Message extends React.Component {

  render() {
    return (
      <div width="50%">
        <div>
          {this.props.text.split('\n').map((t, i) => <div key={i}>{t}</div>)}
        </div>
        <div>
          <button onClick={this.props.deleteMessage}>Delete</button>
        </div>
      </div>
    )
  }
  
}