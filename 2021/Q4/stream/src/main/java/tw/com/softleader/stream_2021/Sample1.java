package tw.com.softleader.stream_2021;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sample1 {

    public static final Comparator<Boolean> BOOLEAN_REVERSED_COMPARATOR = (o1, o2) -> o1 == o2 ? 0 : !o1 ? 1 : -1;

    static final Random random = new Random();
    public static void main(String[] args) {
        var customers = generateSampleDatas();

        var customersByNameContainsA = customers.stream()
            .collect(Collectors.groupingBy(c -> c.getName().contains("A")));
        System.out.println(customersByNameContainsA);

        var uniqueCustomersByNameContainsA = customers.stream()
            .collect(Collectors.groupingBy(c -> c.getName().contains("A"), Collectors.toSet()));
        System.out.println(uniqueCustomersByNameContainsA);

        var uniqueBirthdayByNameContainsA = customers.stream()
            .collect(Collectors.groupingBy(c -> c.getName().contains("A"), Collectors.mapping(Customer::getBirthday, Collectors.toSet())));
        System.out.println(uniqueBirthdayByNameContainsA);

        var customersByReversedOrderNameA = customers.stream().collect(Collectors.groupingBy(
            c -> c.getName().contains("A"),
            () -> new TreeMap<>(BOOLEAN_REVERSED_COMPARATOR),
            Collectors.toList()));
        System.out.println(customersByReversedOrderNameA);

        System.out.println(samplingCustomer(customers));
        System.out.println(samplingCustomer(customers));
        System.out.println(samplingCustomer(customers));
        System.out.println(samplingCustomer(customers));

        System.out.println(customers.stream().collect(new Sampling<>(3)));
        System.out.println(Stream.of(12,3,4,5,6,7,434,2234,4).collect(new Sampling<>(5)));

    }

    private static Optional<Customer> samplingCustomer(List<Customer> customers) {
        return customers.stream().reduce((c1, c2) -> random.nextBoolean() ? c1 : c2);
    }

    private static List<Customer> generateSampleDatas() {
        return List.of(
            Customer.generate("Aiden Ramirez"),
            Customer.generate("Abraham Weston"),
            Customer.generate("Amiee Maynard"),
            Customer.generate("Walid Phillips"),
            Customer.generate("Jesus Donnelly"),
            Customer.generate("Sallie Ahmed"),
            Customer.generate("Zarah Rowland"),
            Customer.generate("Omari Gregory"),
            Customer.generate("Amanah Bullock"),
            Customer.generate("Lola Key"),
            Customer.generate("Issac Rice"),
            Customer.generate("Kieran Morris"),
            Customer.generate("Tracey Hendrix"),
            Customer.generate("Menaal Ramos"),
            Customer.generate("Amy-Leigh Hilton"),
            Customer.generate("Graeme Leech"),
            Customer.generate("Jose Rogers"),
            Customer.generate("Andre Cote"),
            Customer.generate("Ira Mccarthy"),
            Customer.generate("Izabel Sanders"),
            Customer.generate("Amy Davie"),
            Customer.generate("Cruz Head"),
            Customer.generate("Carmen Sampson"),
            Customer.generate("Spike Peel"),
            Customer.generate("Clinton Harrington"),
            Customer.generate("Sahil Jaramillo"),
            Customer.generate("Nial Plummer"),
            Customer.generate("Tyla Frost"),
            Customer.generate("Aoife Mccallum"),
            Customer.generate("Kean Mason"),
            Customer.generate("Lyndsey Beech"),
            Customer.generate("Evalyn Ford"),
            Customer.generate("Ayah Stevenson"),
            Customer.generate("Dalia Wilkins"),
            Customer.generate("Matthew Villalobos"),
            Customer.generate("Jerry Owens"),
            Customer.generate("Kaja Livingston"),
            Customer.generate("Kenan Alvarado"),
            Customer.generate("Sonya Legge"),
            Customer.generate("Rees Armitage"),
            Customer.generate("Ajay Compton"),
            Customer.generate("Olaf Adamson"),
            Customer.generate("Nafeesa Roberson"),
            Customer.generate("Rania Dickerson"),
            Customer.generate("Jeffery Baird"),
            Customer.generate("Clayton Aguirre"),
            Customer.generate("Jaydon Summers"),
            Customer.generate("Chance Medina"),
            Customer.generate("Owen Kouma"),
            Customer.generate("Sania Marriott"));
    }

    static class Sampling<T> implements Collector<T, ArrayList<Sampling.SamplingData<T>>, List<T>> {

        @Getter
        @AllArgsConstructor
        static class SamplingData<T> implements Comparable<SamplingData<T>> {
            Integer token;
            T data;

            @Override public int compareTo(SamplingData that) {
                return this.token.compareTo(that.token);
            }
        }

        final int count;

        Sampling(int count) {
            this.count = count;
        }

        @Override public Supplier<ArrayList<SamplingData<T>>> supplier() {
            return ArrayList::new;
        }

        @Override public BiConsumer<ArrayList<SamplingData<T>>, T> accumulator() {
            return (list, data) -> list.add(new SamplingData<>(random.nextInt(), data));
        }

        @Override public BinaryOperator<ArrayList<SamplingData<T>>> combiner() {
            return (l1, l2) -> {
                l1.addAll(l2);
                return l1;
            };
        }

        @Override public Function<ArrayList<SamplingData<T>>, List<T>> finisher() {
            return sources -> sources.stream()
                .sorted()
                .limit(count)
                .map(SamplingData::getData)
                .collect(Collectors.toList());
        }

        @Override public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
        }
    }

}
