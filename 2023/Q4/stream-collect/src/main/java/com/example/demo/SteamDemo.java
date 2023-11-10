package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class SteamDemo {

  public static void main(String[] args) {

    var intList = IntStream
        .rangeClosed(1, 1000).boxed()
        .collect(Collectors.toList());

    System.out.println(intList.stream().collect(Collectors.averagingInt(x -> x)));

    IntPack intPack = intList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), IntPack::new));

    Map<Integer, List<Integer>> intGroup = intList.stream()
        .collect(Collectors.groupingBy(i -> i % 2, Collectors.filtering(i -> i > 950, Collectors.toList())));
    System.out.println(intGroup);

    List<Integer> intList2 = intGroup.values().stream()
        .collect(Collectors.flatMapping(Collection::stream, Collectors.toList()));

    System.out.println(intList.stream().filter(i -> i > 990).map(i -> i + "").collect(Collectors.joining()));
    System.out.println(intList.stream().filter(i -> i > 990).map(i -> i + "").collect(Collectors.joining(",")));
    System.out.println(intList.stream().filter(i -> i > 990).map(i -> i + "").collect(Collectors.joining(",", "[", "]")));

    System.out.println(intList.stream().filter(i -> i > 990)
        .collect(Collectors.mapping(i -> i / 5, Collectors.toList())));

    System.out.println(intList.stream()
        .collect(Collectors.groupingBy(i -> i % 2, Collectors.filtering(i -> i > 950, Collectors.maxBy(Comparator.naturalOrder())))));

    System.out.println(intList.stream().map(BigDecimal::new).collect(Collectors.reducing(BigDecimal::add)));

    System.out.println(intList.stream().collect(Collectors.summarizingInt(x -> x)));

    System.out.println(intList.stream().collect(Collectors.summingInt(x -> x)));

    PriorityQueue<Integer> queue = intList.stream().collect(Collectors.toCollection(PriorityQueue::new));

    Set<Integer> sampleResult = intList.stream().collect(sampling(5));
    System.out.println("" + sampleResult);
  }

  private static Collector<Integer, List<Integer>, Set<Integer>> sampling(int count) {
    return new Collector<>() {
      @Override public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
      }

      @Override public BiConsumer<List<Integer>, Integer> accumulator() {
        return List::add;
      }

      @Override public BinaryOperator<List<Integer>> combiner() {
        return (list1, list2) -> {
          list1.addAll(list2);
          return list1;
        };
      }

      @Override public Function<List<Integer>, Set<Integer>> finisher() {
        return (list) -> {
          var result = new HashSet<Integer>();
          for (int i = 0; i < count; i++) {
            var randomIdx = Math.random() * list.size();
            var item = list.remove((int) randomIdx);
            result.add(item);
          }
          return result;
        };
      }

      @Override public Set<Characteristics> characteristics() {
        return Set.of(IDENTITY_FINISH);
      }
    };
  }

}
