package tw.com.softleader.trainingexception.base.auth;

import lombok.Getter;

@Getter
public enum Role {

	/** 承辦 */
	WORKER(100),

	/** 科主管 */
	DIVISION_MASTER(200),

	/** 部主管 */
	DEPARTMENT_MASTER(300);

	private int level;

	Role(int level) {
		this.level = level;
	}
}
