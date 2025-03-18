package tw.com.softleader.training.stream_sample;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeSample {

  public static void main(String[] args) {
    calcSumSalary();
    var employees = EmployeeDao.getAllEmployees();
    update(employees);

    // 不要用上面的寫法, 用下面
    employees.stream().forEach(System.out::println);
    employees.forEach(System.out::println);
  }

  private static void update(List<Employee> dtos) {
    var ids = dtos.stream().map(Employee::getId).collect(Collectors.toSet());
    // oracle sql 參數數量 不可以超過 1000 個 (where條件裡面的?), 必須使用 Lists.partition 來限縮一次拋出去的參數數量
    // List<Employee> list = Lists.partition(Lists.newArrayList(ids), 950).stream()
    //    .map(EmployeeDao::getByIdIn)
    //    .flatMap(List::stream)
    //    .toList();
    var dbEntityById = EmployeeDao.getByIdIn(ids).stream()
        .collect(Collectors.toMap(Employee::getId, Function.identity()));

    var toSaveEntities = dtos.stream()
        .map(dto -> {
          var dbEntity = Optional.ofNullable(dbEntityById.get(dto.getId()))
              .orElseThrow(() -> new IllegalArgumentException("id not found:" + dto.getId()));
          dbEntity.setSalary(dto.getSalary());
          return dbEntity;
        })
        .toList();
    EmployeeDao.save(toSaveEntities);
  }

  private static void calcSumSalary() {
    var employees = EmployeeDao.getAllEmployees();

    // 全公司算
    BigDecimal totalSalary = employees.stream()
        .map(Employee::getSalary)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    log.info("全公司薪水: {}", totalSalary);

    // 分年齡層算
    Map<Integer, BigDecimal> empByAge10 = employees.stream()
        .collect(Collectors.groupingBy(
            e -> e.getAge() / 10,
            Collectors.reducing(BigDecimal.ZERO, Employee::getSalary, BigDecimal::add)
        ));

    for (var entry : empByAge10.entrySet()) {
      var age10 = entry.getKey();
      var value = entry.getValue();
      log.info("年齡層為 {}~{} 的薪水總額:{}", age10 * 10, age10 * 10 + 9, value);
    }
  }

}
