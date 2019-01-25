import React, {Component} from 'react';
import './App.css';
import TextInput from "./components/TextInput";
import TextShow from "./components/TextShow";
import { connect } from "react-redux";
import {
  addSeq,
  addTodo,
  addTodoRobert,
  removeTodo,
  updateState
} from "./actions/actions";
class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            textValue: 'World'
        }
    }

    handleChange(e) {
        this.setState({...this.state, textValue: e.target.value});
    }

    handleAdd() {
      //this.props.dispatch(addSeq());
      //this.props.dispatch(addTodo(this.state.textValue));
      this.props.dispatch(updateState(this.state.textValue));
      // this.setState({
        //     ...this.state
        //     , seq: this.state.seq + 1
        //     , textValues: [...this.state.textValues
        //         , {id: this.state.seq, value: this.state.textValue}
        //     ]
        // })
    }

    handleDelete(e, id) {
        this.props.dispatch(removeTodo(id));
    }

    render() {
        return (
            <div className="App">
                <TextInput initText='World'
                           value={this.state.textValue}
                           onChange={e => this.handleChange(e)}
                />
                <button type='button'
                        onClick={e => this.handleAdd(e)}
                >save
                </button>
                <h1 style={{color: 'red'}}>Hello World!!</h1>
                <TextShow values={this.props.textValues}
                          onClick={(e, id) => this.handleDelete(e, id)}
                />
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        textValues: state.todo.textValues
    };
}
export default connect(mapStateToProps)(App);
