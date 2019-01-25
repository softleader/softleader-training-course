const seq = (state = {id : 0}, action) => {
    switch (action.type) {
        case 'ADD_SEQ':
            console.log("ADD_SEQ")
            return {
                ...state,
                id: state.id + 1

            }

        default:
            return state
    }
}



export default seq
