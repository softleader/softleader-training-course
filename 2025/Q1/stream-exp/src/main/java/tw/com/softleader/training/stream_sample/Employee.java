package tw.com.softleader.training.stream_sample;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Employee {
  private Long id;
  private String name;
  private int age;
  private String department;
  private BigDecimal salary;

  public Employee(Long id, String name, int age, String department, double salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.department = department;
    this.salary = BigDecimal.valueOf(salary);
  }

}
