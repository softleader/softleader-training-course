import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Sample3 {

  public static void main(String[] args) {

    var list = IntStream.range(0, 10).boxed().collect(new Collector<Integer, List<Integer>, List<Integer>>() {
      @Override public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
      }

      @Override public BiConsumer<List<Integer>, Integer> accumulator() {
        return List::add;
      }

      @Override public BinaryOperator<List<Integer>> combiner() {
        return (iList1, iList2) -> {
          iList1.addAll(iList2);
          return iList1;
        };
      }

      @Override public Function<List<Integer>, List<Integer>> finisher() {
        return Function.identity();
      }

      @Override public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
      }
    });

    System.out.println(list);

  }

}
