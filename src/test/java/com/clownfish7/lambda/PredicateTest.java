package com.clownfish7.lambda;

import com.clownfish7.Apple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @author You
 * @create 2020-04-11 14:32
 */
public class PredicateTest {

    private List<Apple> filterAppleByWeight(List<Apple> list, Predicate<Apple> predicate) {
        List<Apple> apples = new LinkedList<>();
        list.forEach(item -> {
            if (predicate.test(item)) {
                apples.add(item);
            }
        });
        return apples;
    }

    @Test
    public void testPredicate() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 101),
                new Apple("yellow", 100),
                new Apple("green", 103),
                new Apple("pink", 100)
        );
        filterAppleByWeight(appleList, w -> w.getWeight() > 100).forEach(System.out::println);
    }

    private List<Apple> filterAppleByLongPredicate(List<Apple> list, LongPredicate predicate) {
        List<Apple> apples = new LinkedList<>();
        list.forEach(item -> {
            if (predicate.test(item.getWeight())) {
                apples.add(item);
            }
        });
        return apples;
    }

    @Test
    public void testLongPredicate() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 101),
                new Apple("yellow", 100),
                new Apple("green", 103),
                new Apple("pink", 100)
        );
        filterAppleByLongPredicate(appleList, w -> w > 100).forEach(System.out::println);
    }

    private List<Apple> filterAppleByBiPredicate(List<Apple> list, BiPredicate<String, Long> predicate) {
        List<Apple> apples = new LinkedList<>();
        list.forEach(item -> {
            if (predicate.test(item.getColor(), item.getWeight())) {
                apples.add(item);
            }
        });
        return apples;
    }

    @Test
    public void testBiPredicate() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 101),
                new Apple("yellow", 100),
                new Apple("green", 103),
                new Apple("pink", 100)
        );
        filterAppleByBiPredicate(appleList, (c, w) -> c.equals("blue") && w > 100).forEach(System.out::println);
    }
}
