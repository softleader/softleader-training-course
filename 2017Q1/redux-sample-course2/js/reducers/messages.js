const message = (state = {}, action) => {
  switch (action.type) {
    case 'RECEIVE_MESSAGE':
    case 'RELOAD_MESSAGE':
      return {
        rootId: state.rootId,
        id: state.id,
        text: state.text,
        isEditPanelEnabled: false
      }
    case 'OPEN_EDIT_PANEL':
      if(state.id !== action.id) {
        return state;
      }
      return Object.assign({}, state, {
        isEditPanelEnabled: true
      })
    case 'DELETE_MESSAGE':
      if(state.id !== action.id) {
        return state;
      }
      return undefined;
    default:
      return state;
  }
}

const messages = (state = [], action) => {
  switch (action.type) {
    case 'RECEIVE_MESSAGE':
      return [
        ...state,
        message(action.message, action)
      ]
    case 'RELOAD_MESSAGE':
      return action.messages.map(msg => message(msg, action));
    case 'OPEN_EDIT_PANEL':
    case 'DELETE_MESSAGE':
      return state.map(msg => message(msg, action)).filter(msg => !!msg);
    default:
      return state;
  }
}

export default messages;