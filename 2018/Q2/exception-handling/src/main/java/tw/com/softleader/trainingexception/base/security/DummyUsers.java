package tw.com.softleader.trainingexception.base.security;

import tw.com.softleader.trainingexception.base.auth.ApproveAuth;

public class DummyUsers {

	public static final User WORKER = new User("worker", ApproveAuth.L61);
	public static final User DIVI = new User("divi", ApproveAuth.L51);
	public static final User DEPT = new User("dept", ApproveAuth.L41);

}
