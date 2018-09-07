package tw.com.softleader.tx;

public class Aop implements Say {

	@Override
	public void say() {
		System.out.println("Hello");
		say2();
	}

	public void say2() {
		System.out.println("Hello2");
	}

	static class Aop2 extends Aop {

		@Override
		public void say() {
			System.out.println("Shaking hands");
			super.say();
			System.out.println("Bye");
		}

	}

	static class Aop3 implements Say {

		Say say;

		public Aop3(Say say) {
			super();
			this.say = say;
		}

		@Override
		public void say() {
			System.out.println(this.hashCode() + " Shaking hands");
			say.say();
			System.out.println(this.hashCode() + " Bye");
		}

	}

	public static void main(String[] args) {
		Say aop = new Aop3(new Aop3(new Aop()));
		aop.say();
	}

}
