package tw.com.softleader.training.sample;

import tw.com.softleader.training.data.model.Student;
import tw.com.softleader.training.data.service.StudentService;
import tw.com.softleader.training.utils.TimerUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StreamSample {
	
	/**
	 * 本Class用於演示對一個List做複雜處理時, jdk7與jdk8各自的寫法
	 * 需求:
	 * 取年齡在22與28之間的學生
	 * 將英文名子中有a的人分為A組, 其餘人分成B組
	 * 以英文名字排序
	 * 最後用英文名字+中文姓名組成一個以,分隔的字串
	 */
	public static void main(String[] args) {
//		sameSample();
		
		final StudentService studentService = new StudentService();
		List<Student> students = studentService.getAll();

		String aTeam = students.stream()
			.filter(s -> s.isInRange(22, 28))
			.filter(s -> isATeam(s))
			.sorted(Comparator.comparing(Student::getEngName))
			.map(Student::getFullName)
			.collect(Collectors.joining(","));

		String bTeam = students.stream()
			.filter(s -> s.isInRange(22, 28))
			.filter(s -> isBTeam(s))
			.sorted(Comparator.comparing(Student::getEngName))
			.map(Student::getFullName)
			.collect(Collectors.joining(","));

		System.out.println("A Team: " + aTeam);
		System.out.println("B Team: " + bTeam);

		//		Map<String, List<Student>> studentsByTeam = new HashMap<>();
//		studentsByTeam.put("A", new ArrayList<>());
//		studentsByTeam.put("B", new ArrayList<>());
//
//		for (Student student : students) {
//			int age = student.getAge();
//			if (!(age >= 22 && age <= 28)) {
//				continue;
//			}
//
//			String engName = student.getEngName();
//			if (engName.contains("a") || engName.contains("A")) {
//				studentsByTeam.get("A").add(student);
//			} else {
//				studentsByTeam.get("B").add(student);
//			}
//
//		}
//
//		Comparator<Student> byEngName = Comparator.comparing(Student::getEngName);
//		studentsByTeam.get("A").sort(byEngName);
//		studentsByTeam.get("B").sort(byEngName);
//
//		studentsByTeam.forEach((team, studentsOfTeam) -> {
//			StringBuilder sb = new StringBuilder();
//			sb.append(team).append(": ");
//			for (Student student : studentsOfTeam) {
//				sb.append(student.getEngName())
//					.append("(")
//					.append(student.getLastName())
//					.append(student.getFirstName())
//					.append(")")
//					.append(",");
//			}
//			System.out.println(sb);
//		});
//
//
//				Map<String, String> groupedStudents1 = handleAtJdk7(studentService.getAll());
//		Map<String, String> groupedStudents2 = handleAtJdk8Ver1(studentService.getAll());
//		Map<String, String> groupedStudents3 = handleAtJdk8Ver2(studentService.getAll());
//
//		System.out.println("jdk7");
//		System.out.println("[Team-A]:");
//		System.out.println(groupedStudents1.get("A"));
//		System.out.println("[Team-B]:");
//		System.out.println(groupedStudents1.get("B"));
//
//		System.out.println("jdk8 ver1");
//		System.out.println("[Team-A]:");
//		System.out.println(groupedStudents2.get("A"));
//		System.out.println("[Team-B]:");
//		System.out.println(groupedStudents2.get("B"));
//
//		System.out.println("jdk8 ver2");
//		System.out.println("[Team-A]:");
//		System.out.println(groupedStudents3.get("A"));
//		System.out.println("[Team-B]:");
//		System.out.println(groupedStudents3.get("B"));
//
//		streamTest();
		usedTime();
	}

	private static boolean isBTeam(Student s) {
		return !isATeam(s);
	}

	private static boolean isATeam(Student s) {
		return s.getEngName().contains("a") || s.getEngName().contains("A");
	}

	/**
	 * Stream中四種等價的寫法
	 */
	private static void sameSample() {
		
		List<String> list = Arrays.asList("A","B","C");
		
		// 使用匿名類別
		// 完整表示了所使用的Class, 輸入, 輸出的型別
		list.stream().forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		// 使用lambda(完整)
		// 省略Class的實作與輸出的型別
		list.stream().forEach((String e) -> System.out.println(e));
		
		// 使用lambda(常用)
		// 將Class, 輸入, 輸出的型別全部省略, 只留下函式內容
		list.stream().forEach(e -> System.out.println(e));
		
		// 使用lambda(簡化)
		// 只表達了所使用的函示單元
		list.stream().forEach(System.out::println);
	}
	
	/**
	 * jdk7以前的方法
	 */
	private static Map<String, String> handleAtJdk7(List<Student> students) {
		final Map<String, String> groupedStudents = new HashMap<>();
		
		StringBuilder teamA = new StringBuilder();
		StringBuilder teamB = new StringBuilder();
		
		// 依英文名字排序
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getEngName().compareTo(o2.getEngName());
			}
		});
		
		for (Student student : students) {
			// 篩選可以組隊的學生
			if (isAllowAge(student)) {
				if (getTeamNo(student).equals("A")) {
					// 屬於A隊的組進A隊的字串
					if (teamA.length() > 0) {
						teamA.append(", ");
					}
					teamA.append(studentString(student));
				} else {
					// 屬於B隊的組進B隊的字串
					if (teamB.length() > 0) {
						teamB.append(", ");
					}
					teamB.append(studentString(student));
				}
			}
		}
		
		groupedStudents.put("A", teamA.toString());
		groupedStudents.put("B", teamB.toString());
		
		return groupedStudents;
	}
	
	/**
	 * jdk8以後的方法(使用jdk7的思維)
	 */
	private static Map<String, String> handleAtJdk8Ver1(List<Student> students) {
		final Map<String, String> groupedStudents = new HashMap<>();
		String teamA = students.stream()
			.sorted(Comparator.comparing(student -> student.getEngName())) // 依英文名字排序
			.filter(student -> isAllowAge(student)) // 篩選能組隊的學生
			.filter(student -> "A".equals(getTeamNo(student))) // 篩選A隊的學生
			.map(student -> studentString(student))
			.collect(Collectors.joining(", "));
		
		String teamB = students.stream()
			.sorted(Comparator.comparing(student -> student.getEngName())) // 依英文名字排序
			.filter(student -> isAllowAge(student)) // 篩選能組隊的學生
			.filter(student -> "B".equals(getTeamNo(student))) // 篩選A隊的學生
			.map(student -> studentString(student))
			.collect(Collectors.joining(", "));
		
		groupedStudents.put("A", teamA);
		groupedStudents.put("B", teamB);
		
		return groupedStudents;
	}
	
	/**
	 * jdk8以後的方法
	 */
	private static Map<String, String> handleAtJdk8Ver2(List<Student> students) {
		Map<String, String> groupedStudents = students.stream()
			.sorted(Comparator.comparing(student -> student.getEngName())) // 依英文名字排序
			.filter(student -> isAllowAge(student)) // 篩選能組隊的學生
			.collect(
				// 分組
				Collectors.groupingBy(
					// Key的收集方式
					student -> getTeamNo(student), // (英文名字有A在A隊, 其餘在B隊)
					
					// Value的收集方式
					Collectors.mapping(
						student -> studentString(student), // 每個student轉成字串
						Collectors.joining(", ") // 整個studentList轉成以,區隔的字串
					)
				)
			);
		
		return groupedStudents;
	}
	
	/**
	 * Stream裡可呼叫的方法可以分為intermediate以及terminal的方法
	 * intermediate可以視為一個一個水管, 水管之間可以互相無限組合
	 * terminal可以視為一個水龍頭, 接上後整個水路就完成
	 * 
	 * 在Stream中, 無論呼叫了多少個intermediate, 最後一定都會有一個terminal方法
	 * 在執行terminal方法後才會使整個串流開始執行
	 */
	private static void streamTest(List<Student> students) {
		
		students.stream()
				// intermediate
				.sorted(Comparator.comparing(student -> student.getEngName())) // 依英文名字排序
				
				// intermediate
				.filter(student -> {
					System.out.println();
					System.out.println("Filter1\t" + student.getEngName() + " Age:" + student.getAge());
					return isAllowAge(student);
				})
				
				// intermediate
				// peek的一個特性是可能在任何時候或任何thread中被執行
				// javadoc的官方說法是這個方法一般是使用在debug中
				.peek(student -> System.out.println("Pick\t" + student.getEngName()))
				
				// intermediate
				.filter(student -> {
					System.out.println("Filter2\t" + student.getEngName() + " Team:" + getTeamNo(student));
					return "A".equals(getTeamNo(student));
				})
				
				// intermediate
				.map(student -> studentString(student))
				
				// terminal
				.collect(Collectors.joining(", "));
		
	}
	
	/** 判斷student是否符合年齡限制 */
	private static boolean isAllowAge(Student student) {
		return student.getAge() >= 22 && student.getAge() <= 28;
	}
	
	/** 判斷student屬於哪個隊伍 */
	private static String getTeamNo(Student student) {
		if (isATeam(student)) {
			return "A";
		} else {
			return "B";
		}
	}
	
	private static String studentString(Student student) {
		return new StringBuilder().append("(").append(student.getEngName()).append(")")
				.append(student.getLastName()).append(student.getFirstName()).toString();
	}
	
	/**
	 * 每種方法所消耗的時間
	 */
	public static void usedTime() {
		final StudentService studentService = new StudentService();
		
		List<Student> students = studentService.getNStudent(10000);
		
		final Duration duration1 = TimerUtils.runNTimesCost(() -> handleAtJdk7(students), 1000);
		final Duration duration2 = TimerUtils.runNTimesCost(() -> handleAtJdk8Ver2(students), 1000);
		
		System.out.println("handleAtJdk7:\t" + duration1.toMillis() + "ms");
		System.out.println("handleAtJdk8:\t" + duration2.toMillis() + "ms");
	}

}
