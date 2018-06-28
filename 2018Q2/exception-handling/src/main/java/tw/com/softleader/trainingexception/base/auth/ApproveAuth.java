package tw.com.softleader.trainingexception.base.auth;

import lombok.Getter;

@Getter
public enum ApproveAuth {

	/** 無 */
	EMPTY(0),
	/** 6.2承辦 */
	L62(Role.WORKER, 100),
	/** 6.1承辦*/
	L61(Role.WORKER, 200),
	/** 5.2科主管 */
	L52(Role.DIVISION_MASTER, 300),
	/** 5.1科主管 */
	L51(Role.DIVISION_MASTER, 400),
	/** 4.2部主管 */
	L42(Role.DEPARTMENT_MASTER, 500),
	/** 4.1部主管 */
	L41(Role.DEPARTMENT_MASTER, 600),
	;

	private Role role;
	private int level;

	ApproveAuth(int level) {
		this.role = null;
		this.level = level;
	}

	ApproveAuth(Role role, int level) {
		this.role = role;
		this.level = level;
	}
}
