import { combineReducers } from 'redux'
import todo from './todos'
import seq from './seq'

const todoApp = combineReducers({
    todo,
    seq
})

export default todoApp
