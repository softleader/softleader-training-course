package tw.com.softleader.training.stream_sample;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
public class EmployeeDao {

  public static final List<Employee> ALL = Arrays.asList(
      new Employee(1L, "Alice", 25, "Engineering", 70000),
      new Employee(2L, "Bob", 30, "Engineering", 85000),
      new Employee(3L, "Charlie", 28, "HR", 60000),
      new Employee(4L, "David", 35, "HR", 75000),
      new Employee(5L, "Eve", 40, "Finance", 90000),
      new Employee(6L, "Frank", 29, "Finance", 72000),
      new Employee(7L, "Grace", 27, "Engineering", 68000),
      new Employee(8L, "Hank", 32, "Marketing", 65000),
      new Employee(9L, "Ivy", 26, "Marketing", 62000),
      new Employee(10L, "Jack", 45, "Finance", 100000)
  );

  public static List<Employee> getAllEmployees() {
    return ALL;
  }

  public static Optional<Employee> getById(Long id) {
    log.warn("DB I/O");
    return ALL.stream()
        .filter(e -> e.getId().equals(id))
        .findFirst();
  }

  public static List<Employee> getByIdIn(Collection<Long> ids) {
    log.warn("DB I/O");
    return ALL.stream()
        .filter(e -> ids.contains(e.getId()))
        .toList();
  }

  public static void save(Employee entity) {
    log.warn("DB I/O");
  }

  public static void save(Collection<Employee> entities) {
    log.warn("DB I/O");
  }

}
