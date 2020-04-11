package com.clownfish7.lambda;

import com.clownfish7.Apple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * @author You
 * @create 2020-04-11 14:42
 */
public class FuncationTest {

    private List<Apple> appleList = Arrays.asList(
            new Apple("red", 100),
            new Apple("blue", 101),
            new Apple("yellow", 100),
            new Apple("green", 103),
            new Apple("pink", 100)
    );

    private Apple appleFuncation(Apple apple, Function<Apple, Apple> function) {
        return function.apply(apple);
    }

    @Test
    public void testFuncation() {
        for (Apple apple : appleList) {
            System.out.println(appleFuncation(apple, (app) -> {
                apple.setWeight(app.getWeight() * 10);
                return apple;
            }));
        }
    }

    @Test
    public void testIntFuncation() {
        IntFunction<Long> intFunction = a -> (long) a;
        Integer intA = 1;
        Long LongA = intFunction.apply(intA);
        System.out.println(intA.getClass());
        System.out.println(LongA.getClass());
    }

    @Test
    public void testBiFuncation() {
        BiFunction<String, Long, Apple> biFunction = Apple::new;
        System.out.println(biFunction.apply("red", 100L));
    }
}
