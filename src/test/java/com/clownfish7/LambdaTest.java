package com.clownfish7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author yzy
 * @classname LambdaTest
 * @description TODO
 * @create 2020-04-10 11:03 AM
 */
public class LambdaTest {

    @Test
    public void test() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 101),
                new Apple("yellow", 103),
                new Apple("green", 102),
                new Apple("pink", 99)
        );
        appleList.sort((a, b) ->
                (int) (a.getWeight() - b.getWeight())
        );
        appleList.sort(Comparator.comparing(Apple::getWeight));
        appleList.forEach(System.out::println);
        Supplier<Apple> supplier = () -> new Apple("", 1);
    }

    @Test
    public void testUsage() {
        Runnable r1 = () -> System.out.println("1");
        r1.run();
    }
}
