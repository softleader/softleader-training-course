import AppDispatcher from "../dispatcher/AppDispatcher.jsx";
import {PolEvents} from "../constants/Events.jsx";
import $ from "jquery";

class SmsStore {

  constructor() {
		// super();
	}

	/**
	 * handleSmsSubmit
	 */
	handleSmsSubmit(data) {
		$.ajax({
      url: "http://localhost:3001/react/workshop/sms",
      dataType: 'json',
      contentType: 'application/json; charset=UTF-8',
      type: 'POST',
      data: JSON.stringify({sendMail: true}),
      success: function(data, textStatus, jqXHR) {
				console.debug(data.message);
      }.bind(this),
      error: function(jqXHR, textStatus, errorThrown) {
        console.error(errorThrown);
      }
    });
	}

}

const smsStore = new SmsStore();
export default smsStore;

smsStore.dispatcherIndex = AppDispatcher.register(payload => {
	const action = payload.action;

	switch(action.eventName) {
		case PolEvents.POL_SUBMIT:
      smsStore.handleSmsSubmit(action.data);
			break;
	}

});