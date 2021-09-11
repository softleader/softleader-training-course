package tw.com.softleader.training.sample;

import tw.com.softleader.training.data.model.Student;
import tw.com.softleader.training.data.service.StudentService;
import tw.com.softleader.training.utils.TimerUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortSample {
	
	public static void main(String[] args) {
		
		final StudentService studentService = new StudentService();
		final List<Student> students = studentService.getAll();
		
		// 可以抽換為sortAgeAtJdk7, sortAgeAtJdk8Ver1, sortAgeAtJdk8Ver2, sortAgeAtJdk8Ver3
//		final List<Student> sortedStudents = sortAgeAtJdk7(students);
		
//		sortedStudents.forEach(student -> System.out.println(student));
		
		usedTime();
	}
	
	/**
	 * 以基礎型別排序 jdk7以前的方法
	 */
	public static List<Student> sortAgeAtJdk7(final List<Student> students) {
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return Integer.compare(o1.getAge(), o2.getAge());
			}
		});
		
		return students;
	}

	/**
	 * 以基礎型別排序 jdk8以後的方法1
	 * 1. Comparator不再自己實作, 而是呼叫Comparator.comparing, 並提供需要排序的欄位
	 * 2. 匿名類別改用Lambda取代
	 */
	public static List<Student> sortAgeAtJdk8Ver1(final List<Student> students) {
		Collections.sort(students, Comparator.comparing(student -> student.getAge())); // 依年齡排序
		
		return students;
	}
	
	/**
	 * 以基礎型別排序 jdk8以後的方法2
	 * 1. 直接呼叫List本身提供的sort方法
	 * 2. Comparator不再自己實作, 而是使用Comparator.comparing
	 * 3. 匿名類別改用Lambda取代
	 */
	public static List<Student> sortAgeAtJdk8Ver2(final List<Student> students) {
		students.sort(Comparator.comparing(student -> student.getAge())); // 依年齡排序
		
		return students;
	}
	
	/**
	 * 以基礎型別排序 jdk8以後的方法3
	 * 1. 採用stream進行排序
	 * 2. 與前面的差異點在於students本身並沒有被排序, 因此不會異動到原始資料
	 */
	public static List<Student> sortAgeAtJdk8Ver3(final List<Student> students) {
		final List<Student> newStudents =
				students.stream()
					.sorted(Comparator.comparing(student -> student.getAge())) // 依年齡排序
					.collect(Collectors.toList()); // 蒐集成List
		
		return newStudents;
	}
	
	/**
	 * 以多個欄位進行排序
	 * 本方法目的用於演示jdk8進行多欄位的排序方式
	 */
	public static List<Student> sortMultiAtJdk8(final List<Student> students) {
		final List<Student> newStudents = 
				students.stream()
					.sorted(Comparator // 依條件排序
							.<Student>comparingInt(student -> student.getAge()) // 先以年齡排序
							.thenComparingLong(student -> student.getStudentId()) // 再以學生id排序
					)
					.collect(Collectors.toList()); // 蒐集成List
		
		return newStudents;
	}
	
	/**
	 * 每種排序方法所消耗的時間
	 */
	public static void usedTime() {
		final StudentService studentService = new StudentService();
		
		// 各自建立10萬個樣本數來跑排序
		final List<Student> students1 = studentService.getNStudent(1000000);
		final List<Student> students2 = studentService.getNStudent(1000000);
		final List<Student> students3 = studentService.getNStudent(1000000);
		final List<Student> students4 = studentService.getNStudent(1000000);

		final Duration duration1 = TimerUtils.runNTimesCost(() -> sortAgeAtJdk7(students1), 1);
		final Duration duration2 = TimerUtils.runNTimesCost(() -> sortAgeAtJdk8Ver1(students2), 1);
		final Duration duration3 = TimerUtils.runNTimesCost(() -> sortAgeAtJdk8Ver2(students3), 1);
		final Duration duration4 = TimerUtils.runNTimesCost(() -> sortAgeAtJdk8Ver3(students4), 1);
		
		System.out.println("SortAgeAtJdk7:\t\t" + duration1.toMillis() + "ms");
		System.out.println("SortAgeAtJdk8Ver1:\t" + duration2.toMillis() + "ms");
		System.out.println("SortAgeAtJdk8Ver2:\t" + duration3.toMillis() + "ms");
		System.out.println("SortAgeAtJdk8Ver3:\t" + duration4.toMillis() + "ms");
	}
	
}
