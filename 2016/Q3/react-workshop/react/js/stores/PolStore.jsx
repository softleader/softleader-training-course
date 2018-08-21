import AppDispatcher from "../dispatcher/AppDispatcher.jsx";
import {EventEmitter} from 'events';
import {CommonEvents, PolEvents} from "../constants/Events.jsx";
import $ from "jquery";

const _datas = {formData: {}}; // 儲存 Store 處理完的資料

class PolStore extends EventEmitter {

  constructor() {
		super();
	}

	/**
	 * 提供 View 或其他 Store 取得當前處理完的 data
	 */
	getDatas() {
		return _datas;
	}

	/**
	 * 提供 View 註冊此 Store 的 DATA_CHANGE 事件
	 */
	addChangeListener(callback) {
		this.on(CommonEvents.DATA_CHANGE, callback);
	}

	/**
	 * 提供 View 反註冊此 Store 的 DATA_CHANGE 事件
	 */
	removeChangeListener(callback) {
		this.removeListener(CommonEvents.DATA_CHANGE, callback);
	}

	/**
	 * 業務邏輯，處理完後會透過 this.emit() 發佈 DATA_CHANGE 事件
	 */
	handleSubmit(data) {
		$.ajax({
      url: "http://localhost:3001/react/workshop",
      dataType: 'json',
      contentType: 'application/json; charset=UTF-8',
      type: 'POST',
      data: JSON.stringify(data),
      success: function(data, textStatus, jqXHR) {
        _datas.formData = data;
				console.debug(data.message);
				this.emit(CommonEvents.DATA_CHANGE);
      }.bind(this),
      error: function(jqXHR, textStatus, errorThrown) {
        console.error(errorThrown);
      }
    });
	}

}

/**
 * 此處是 Store 向 AppDispatcher 註冊想要關注的事件
 */
const polStore = new PolStore();
export default polStore;

polStore.dispatcherIndex = AppDispatcher.register(payload => {
	const action = payload.action;

	switch(action.eventName) {
		case PolEvents.POL_SUBMIT:
      polStore.handleSubmit(action.data);
			break;
	}

});