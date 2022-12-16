import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sample1 {

  public static void main(String[] args) {

    //    var plus = new Calc() {
    //      @Override public int doCalc(int x, int y) {
    //        return x + y;
    //      }
    //    };

    //    Calc plus = (x, y) -> x + y;

    Calc plus = Integer::sum;

    System.out.println(plus.doCalc(4, 6));

    var list = new ArrayList<Integer>();
    IntStream.range(0, 10).mapToObj(Integer::valueOf).forEach(list::add);
    System.out.println(list);

    var numbers = IntStream.range(0, 10).mapToObj(BigDecimal::valueOf).collect(Collectors.toList());
    System.out.println(numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add));

  }
}
