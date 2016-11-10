import AppDispatcher from "../dispatcher/AppDispatcher.jsx";
import {PolEvents} from "../constants/Events.jsx"

class PolAction {

	constructor() {
		console.debug("PolAction isInit.");
	}

	handleSubmit(data) {
		AppDispatcher.handleViewAction({
			eventName: PolEvents.POL_SUBMIT,
			data: data
		});
	}

}

export default new PolAction();