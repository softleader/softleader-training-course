import AppDispatcher from "../dispatcher/AppDispatcher.jsx";
import {EventEmitter} from 'events';
import {CommonEvents, PolEvents} from "../constants/Events.jsx";
import $ from "jquery";

const _datas = {formData: {}};

class PolStore extends EventEmitter {

  constructor() {
		super();
	}

	getDatas() {
		return _datas;
	}

	addChangeListener(callback) {
		this.on(CommonEvents.DATA_CHANGE, callback);
	}

	removeChangeListener(callback) {
		this.removeListener(CommonEvents.DATA_CHANGE, callback);
	}

	/**
	 * handleSubmit
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