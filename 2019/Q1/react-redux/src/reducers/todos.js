const initialState = {
    textValues: []
};
const todo = (state = initialState, action) => {
    switch (action.type) {
        case 'ADD_TODO':
          console.log("ADD_TODO",action.id);
            let newState = {
                ...state,
                textValues: [...state.textValues
                    , {id: action.id, value: action.text}
                ]
            };
            return newState;
        case 'ADD_TODO_ROBERT':
            return {
              ...state,
              textValues: [...state.textValues
                , {id: action.id, value: action.text+ '_Robert'}
              ]
            };;
        case 'REMOVE_TODO':
            let newTextValues = state.textValues.slice();
            state.textValues
                .filter(v => v.id === action.id)
                .forEach(v => {
                    newTextValues.splice(newTextValues.indexOf(v), 1);
                });

            return{
                ...state
                , textValues: newTextValues
            };

        default:
            return state
    }
}



export default todo
