package com.clownfish7.collector;

import com.clownfish7.Dish;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @author You
 * @create 2020-04-12 16:43
 */
public class CollectorsTest {

    private List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void testAveragingInt() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testAveragingDouble() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testAveragingLong() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testCollectingAndThen() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "the average is " + a)))
                .ifPresent(System.out::println);

        Optional.ofNullable(menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testCounting() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.counting()))
                .ifPresent(System.out::println);

    }

    @Test
    public void testGroupingByFunction() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testGroupingByFunctionAndCollector() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting())))
                .ifPresent(System.out::println);
    }

    @Test
    public void testGroupingByFunctionAndSupplierAndCollector() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.counting())))
                .ifPresent(System.out::println);
    }

    @Test
    public void testSummarizingInt() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testGroupingByConcurrentFunction() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testGroupingByConcurrentFunctionAndCollector() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.counting())))
                .ifPresent(System.out::println);
    }

    @Test
    public void testGroupingByConcurrentFunctionAndSupplierAndCollector() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentHashMap::new, Collectors.counting())))
                .ifPresent(System.out::println);
    }

    @Test
    public void testJoining() {
        Optional.ofNullable(menu.stream()
                .map(dish -> dish.getName())
                .collect(Collectors.joining()))
                .ifPresent(System.out::println);
        Optional.ofNullable(menu.stream()
                .map(dish -> dish.getName())
                .collect(Collectors.joining("|")))
                .ifPresent(System.out::println);
        Optional.ofNullable(menu.stream()
                .map(dish -> dish.getName())
                .collect(Collectors.joining("|", "pre ", " suf")))
                .ifPresent(System.out::println);
    }

    @Test
    public void testMapping() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.mapping(Dish::getName, Collectors.joining())))
                .ifPresent(System.out::println);
        Optional.ofNullable(menu.stream()
                .collect(Collectors.mapping(dish -> dish.getName(), Collectors.joining("|"))))
                .ifPresent(System.out::println);
        Optional.ofNullable(menu.stream()
                .collect(Collectors.mapping(dish -> dish.getName(), Collectors.joining("|", "pre ", " suf"))))
                .ifPresent(System.out::println);
    }

    @Test
    public void testMaxByMinBy() {
        menu.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
        menu.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
        menu.stream().max(Comparator.comparingInt(Dish::getCalories)).ifPresent(System.out::println);
        menu.stream().min(Comparator.comparingInt(Dish::getCalories)).ifPresent(System.out::println);
    }

    @Test
    public void testPartition() {
        Optional.of(
                menu.stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian)))
                .ifPresent(System.out::println);
        Optional.of(
                menu.stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting())))
                .ifPresent(System.out::println);
    }

    @Test
    public void testReducing() {
        menu.stream()
                .collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
        Optional.of(menu.stream()
                .map(Dish::getCalories)
                .collect(Collectors.reducing(0, (a, b) -> a + b)))
                .ifPresent(System.out::println);
        Optional.of(menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (a, b) -> a + b)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testSummarizing() {
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    @Test
    public void testSumming() {
        Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.of(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
                .ifPresent(System.out::println);
    }

    @Test
    public void test2Collections() {
        Optional.of(menu.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toCollection(LinkedList::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    @Test
    public void test2ListOrSet() {
        Optional.of(menu.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toList()))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        Optional.of(menu.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toSet()))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    @Test
    public void test2Map() {
        Optional.of(menu.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toMap(Dish::getName, Dish::getType)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        Optional.of(menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1, Integer::sum)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        Optional.of(menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1, Integer::sum, TreeMap::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

    @Test
    public void test2ConcurrentMap() {
        Optional.of(menu.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getType)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1, Integer::sum)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        Optional.of(menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1, Integer::sum, ConcurrentSkipListMap::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        Optional.of(menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.toConcurrentMap(Dish::getType, v -> 1, Integer::sum),Collections::unmodifiableMap)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }
}
