import React from "react";
import PolStore from "../stores/PolStore.jsx";
import PolAction from "../actions/PolAction.jsx";
import es6BindAll from "es6bindall";

export default class PolForm extends React.Component {

  constructor(props) {
    super(props); // 如果要在 constructor 裡使用 this.props 則要執行 super(props)，否則使用 super() 即可
    this.state = {name1: "1"}; // 設定 state 的 default value
    
    es6BindAll(this, [ // 如果要在自訂義的 method 中使用 this，則必須在 constructor 中進行 bind，因為 react 預設不會去 bind 任何 object
      "_onChange", "handleInputChange"
    ]);
  }

  /**
   * React lifeCycle method: Component render 完後就會執行此 method，在此處我們向 PolStore 註冊了 DATA_CHANGE 事件，callback method 為 _onChange
   */
  componentDidMount() {
    PolStore.addChangeListener(this._onChange);
  }

  /**
   * React lifeCycle method: Component 移除後就會執行此 method，在此處我們向 PolStore 反註冊了 DATA_CHANGE 事件
   */
  componentWillUnmount() {
    PolStore.removeChangeListener(this._onChange);
  }

  /**
   * 一但 PolStore 發佈了 DATA_CHANGE 事件，此 method 就會被調用，並且從 PolStore 取回我們需要的 data，更新 Component 的 state
   * setState 一被調用，react 就會重新執行 render()
   */
  _onChange() {
    this.setState(PolStore.getDatas().formData);
  }

  /**
   * 當 input 發生 onChange 事件時，我們即時取得 user 在 input 中輸入的值並更新 react state
   */
  handleInputChange(e) {
    var obj = {};
    obj[e.target.name] = e.target.value;
    this.setState(obj);
  }

  render() {

    return (
      <form>
        {/* 
          此處我們使用 value 來 bind state 中的某個欄位，onChange 則負責 bind user 在 input 中輸入的值，如此達到 two way bind(data source <> view) 
          如果要達到 One-way from data source to view，則不需要 onChange
          若是要達到 One-way from view to data source，則不需要 value，但如果此時想指定預設值就要使用 defaultValue，不可再使用 value
        */}
        <input type="text" name="name1" value={this.state.name1} onChange={this.handleInputChange} /> <br />
      </form>
    );
  }

}