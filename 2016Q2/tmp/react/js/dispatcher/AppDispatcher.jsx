import {Dispatcher} from "flux";

class AppDispatcher extends Dispatcher {

	constructor() {
		super();
		console.debug("AppDispatcher isInit.");
	}

	handleViewAction(action) {
		this.dispatch({
			source: 'VIEW_ACTION',
			action: action
		});
	}

	handleServerAction(action) {
		this.dispatch({
			source: 'SERVER_ACTION',
			action: action
		});
	}

}

export default new AppDispatcher();