import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Random;
import java.util.UUID;

public class Customer {
  static final Random random = new Random();
  static final int birthday_year_max = 2000;
  static final int birthday_year_min = 1970;
  String name;
  LocalDate birthday;
  String password;

  static Customer generate(String name) {
    var year = random.nextInt(birthday_year_max - birthday_year_min) + birthday_year_min;
    var month = random.nextInt(12 - 1) + 1;
    var days = YearMonth.of(year, month).lengthOfMonth();
    var dayOfMonth = random.nextInt(days - 1) + 1;
    var customer = new Customer();
    customer.setName(name);
    customer.setBirthday(LocalDate.of(year, month, dayOfMonth));
    customer.setPassword(UUID.randomUUID().toString() + "_lower");
    return customer;
  }

  @Override public String toString() {
    return "Customer{" +
        "name='" + name + '\'' +
        '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}