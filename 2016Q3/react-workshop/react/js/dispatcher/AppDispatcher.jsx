import {Dispatcher} from "flux";

class AppDispatcher extends Dispatcher {

	constructor() {
		super();
		console.debug("AppDispatcher isInit.");
	}

	/**
	 * 接收 action 發送過來的請求，完成事件的發佈，提供給 view 端使用
	 */
	handleViewAction(action) {
		this.dispatch({
			source: 'VIEW_ACTION',
			action: action
		});
	}

	/**
	 * 接收 action 發送過來的請求，完成事件的發佈，提供給 Server 端使用
	 */
	handleServerAction(action) {
		this.dispatch({
			source: 'SERVER_ACTION',
			action: action
		});
	}

}

export default new AppDispatcher();