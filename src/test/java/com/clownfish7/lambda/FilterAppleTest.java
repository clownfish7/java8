package com.clownfish7.lambda;

import com.clownfish7.Apple;
import com.clownfish7.AppleFilter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author yzy
 * @classname FilterApple
 * @description TODO
 * @create 2020-04-09 7:26 PM
 */
public class FilterAppleTest {

    @Test
    public void test() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 100),
                new Apple("yellow", 100),
                new Apple("green", 100),
                new Apple("pink", 100)
        );
        List<Apple> result = appleList.stream()
                .filter(item -> item.getColor().equals("red"))
                .collect(Collectors.<Apple>toList());
        assert result.size() == 1;
        assert result.get(0).getColor().equals("red") : "haha";
    }

    @Test
    public void testFilterApple() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 100),
                new Apple("yellow", 100),
                new Apple("green", 100),
                new Apple("pink", 100)
        );
        List<Apple> result = filterApple(appleList, apple -> apple.getColor().equals("red"));
        assert result.size() == 1;
        assert result.get(0).getColor().equals("red") : "haha";
    }

    private List<Apple> filterApple(List<Apple> list, AppleFilter appleFilter) {
        List<Apple> apples = new LinkedList<>();
        list.forEach(item -> {
            if (appleFilter.filter(item)) {
                apples.add(item);
            }
        });
        return apples;
    }

    @Test
    public void testThreadRun() {
        new Thread(() ->
                System.out.println("heihei")
        ).start();
    }

//    --------------------------------------- Predicate ---------------------------------------------

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
