package tw.com.softleader.training.data.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tw.com.softleader.training.data.model.Student;

public class StudentService {
	
	private final static List<Student> ALL_STUDENTS = Arrays.asList(
		new Student(1,"李","日貴","Gary",randomAge()),
		new Student(5,"李","日晟","Jack",randomAge()),
		new Student(16,"林","怡辰","Alice",randomAge()),
		new Student(17,"范姜","翔煒","John",randomAge()),
		new Student(28,"劉","千瑜","Naomi",randomAge()),
		new Student(33,"何","適宇","Matt",randomAge()),
		new Student(37,"林","宛葶","Stacey",randomAge()),
		new Student(54,"徐","永維","David",randomAge()),
		new Student(60,"劉","和昇","Sam",randomAge()),
		new Student(63,"李","昱勳","Ken",randomAge()),
		new Student(69,"鍾","傑安","Ray",randomAge()),
		new Student(70,"畢","惠婷","Lisa",randomAge()),
		new Student(76,"方","韻馨","Vicky",randomAge()),
		new Student(78,"蔡","承璋","Chris",randomAge()),
		new Student(82,"傅","炤棋","French",randomAge()),
		new Student(83,"盧","泓民","Joshua",randomAge()),
		new Student(84,"翁","聖昌","Rocky",randomAge()),
		new Student(85,"林","修逸","Toba",randomAge()),
		new Student(86,"黃","冠霖","Frank",randomAge()),
		new Student(91,"徐","瀅絜","Sunny",randomAge()),
		new Student(93,"許","碩容","Winni",randomAge()),
		new Student(94,"劉","怡君","Ellie",randomAge()),
		new Student(101,"黃","柏瑞","Bruce",randomAge()),
		new Student(103,"陳","蔚佳","Joyce",randomAge()),
		new Student(105,"蔡","應騰","Allen",randomAge()),
		new Student(106,"陳","炳弘","Benny",randomAge()),
		new Student(114,"董","昱潔","Lion",randomAge()),
		new Student(115,"洪","月銘","Steven",randomAge()),
		new Student(116,"林","鈜澤","Van",randomAge()),
		new Student(119,"劉","淑怡","Zoe",randomAge()),
		new Student(124,"莊","閔翔","Glen",randomAge()),
		new Student(125,"姜","鈞傑","Jerry",randomAge()),
		new Student(130,"李","英傑","Tim",randomAge()),
		new Student(132,"張","鼎鑫","Rhys",randomAge()),
		new Student(133,"林","柏廷","Tim",randomAge()),
		new Student(134,"劉","威進","Willy",randomAge()),
		new Student(135,"張","芷瑄","Hilda",randomAge()),
		new Student(137,"沈","德祐","Dennis",randomAge()),
		new Student(139,"翁","翊庭","Meta",randomAge()),
		new Student(141,"李","雙雙","Irene",randomAge()),
		new Student(142,"高","凡","Ines",randomAge()),
		new Student(143,"陳","旭晨","Leo",randomAge()),
		new Student(144,"張","俊麒","Ryan",randomAge())
	);
	
	private static int randomAge() {
		return (int)((Math.random() * 10D) + 20);
	}
	
	/**
	 * 取得全部的學生
	 */
	public List<Student> getAll() {
		// 為了避免重複作業造成方法之間處理的資料不一致, 為公平化每次都要送一個新的instance出去
		return ALL_STUDENTS.stream().collect(toList());
	}
	
	/**
	 * 隨機產生N筆學生
	 */
	public List<Student> getNStudent(int Times) {
		List<Student> students = new ArrayList<>();
		for (int i = 1; i <= Times; i++) {
			students.add(new Student(i, "名字"+i, "姓氏"+i, "Eng"+i+(i%2==0?"A":"B"), randomAge()));
		}
		return students;
	}

}
