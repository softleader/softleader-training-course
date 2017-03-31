import fetch from 'isomorphic-fetch'

export const receiveMessage = (message) => {
  return {
    type: 'RECEIVE_MESSAGE',
    message
  }
}

export const reloadMessage = (messages) => {
  return {
    type: 'RELOAD_MESSAGE',
    messages
  }
}

export const addMessage = (rootId, text) => {
  return function(dispatch) {
    return fetch('http://localhost:3000/messages', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }, 
        body: JSON.stringify({
          rootId, text
        })})
      .then(response => response.json())
      .then(json => dispatch(receiveMessage(json)))
  }
}

export const readMessage = (rootId) => {
  return function(dispatch) {
    return fetch('http://localhost:3000/messages?rootId=' + rootId, {method: 'GET'})
      .then(response => response.json())
      .then(json => dispatch(reloadMessage(json)))
  }
}

export const openEditPanel = (id) => {
  return {
    type: 'OPEN_EDIT_PANEL',
    id
  }
}

export const deleteMessage = (rootId, id) => {
  return function(dispatch) {
    return fetch('http://localhost:3000/messages', {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }, 
        body: JSON.stringify({
          rootId, id
        })})
      .then(response => 
        dispatch({
          type: 'DELETE_MESSAGE',
          id
        }))
  }
}