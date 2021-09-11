package tw.com.softleader.training.data.model;

public class Student {
	
	private long studentId;
	
	private String firstName;
	
	private String lastName;
	
	private String engName;
	
	private int age;

	public Student() {
		super();
	}

	public Student(long studentId, String lastName, String firstName, String engName, int age) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.engName = engName;
		this.age = age;
	}

	public String getFullName() {
		return getEngName() + "(" + getLastName() + getFirstName() + ")";
	}

	public boolean isInRange(int min, int max) {
				return getAge() >= min && getAge() <= max;
		}

    public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", engName=" + engName + ", age=" + age + "]";
	}
}
