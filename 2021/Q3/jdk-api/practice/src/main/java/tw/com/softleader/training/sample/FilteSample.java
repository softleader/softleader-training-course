package tw.com.softleader.training.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tw.com.softleader.training.data.model.Student;
import tw.com.softleader.training.data.service.StudentService;

public class FilteSample {
	
	public static void main(String[] args) {
		
		final StudentService studentService = new StudentService();
		final List<Student> students = studentService.getAll();
		
		// 可以抽換為filteNameAtJdk7, filteNameAtJdk8
		final List<Student> filtedStudents = filteNameAtJdk7(students);
		
		filtedStudents.forEach(student -> System.out.println(student));
		
	}
	
	/**
	 * 進行篩選, jdk7以前的方法
	 */
	private static List<Student> filteNameAtJdk7(final List<Student> students) {
		final List<Student> result = new ArrayList<Student>();
		
		for (Student student : students) {
			if (isNeed(student)) {
				result.add(student);
			}
		}
		
		return result;
	}
	
	/**
	 * 進行篩選, jdk8以後的方法
	 * 1. Stream中提供了filter的方法, 此方法需要描述一個布林值的產生方式
	 * 2. 透過Stream處理, 若要再以List接出, 需要額外呼叫collect(Collectors.toList())
	 */
	private static List<Student> filteNameAtJdk8(final List<Student> students) {
		return students.stream()
				.filter(student -> isNeed(student)) // 依條件進行篩選
				.collect(Collectors.toList()); // 蒐集成List
	}
	
	private static boolean isNeed(Student student) {
		return student.getLastName().equals("李");
	}
		
}
