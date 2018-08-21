import AppDispatcher from "../dispatcher/AppDispatcher.jsx";
import {PolEvents} from "../constants/Events.jsx";
import $ from "jquery";

class MailStore {

  constructor() {
		// super();
	}

	/**
	 * handleMailSubmit
	 */
	handleMailSubmit(data) {
		$.ajax({
      url: "http://localhost:3001/react/workshop/mail",
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

const mailStore = new MailStore();
export default mailStore;

mailStore.dispatcherIndex = AppDispatcher.register(payload => {
	const action = payload.action;

	switch(action.eventName) {
		case PolEvents.POL_SUBMIT:
      mailStore.handleMailSubmit(action.data);
			break;
	}

});