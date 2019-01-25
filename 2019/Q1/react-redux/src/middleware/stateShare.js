 const stateShare =  store => next => action => {
    const state = store.getState();
    const { type } = action;

    switch (type) {
        case 'ADD_TODO':{
          console.log("MIDDLE")
            action.id = state.seq.id;
            break;
        }
      default:
          break;
    }
    next(action);


}

 export default stateShare;