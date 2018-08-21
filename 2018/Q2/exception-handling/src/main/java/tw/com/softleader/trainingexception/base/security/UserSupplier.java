package tw.com.softleader.trainingexception.base.security;

public class UserSupplier {

	private static ThreadLocal<User> loginUserAcct = new ThreadLocal<>();

	public static AutoCloseable set(User loginUserAcct) {
		UserSupplier.loginUserAcct.set(loginUserAcct);
		return UserSupplier::clear;
	}

	public static User get() {
		return UserSupplier.loginUserAcct.get();
	}

	public static void clear() {
		UserSupplier.loginUserAcct.remove();
	}

}
