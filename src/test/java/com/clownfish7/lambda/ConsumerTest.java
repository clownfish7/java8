package com.clownfish7.lambda;

import com.clownfish7.Apple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author You
 * @create 2020-04-11 14:33
 */
public class ConsumerTest {

    List<Apple> appleList = Arrays.asList(
            new Apple("red", 100),
            new Apple("blue", 101),
            new Apple("yellow", 100),
            new Apple("green", 103),
            new Apple("pink", 100)
    );

    private void appleConsumer(List<Apple> list, Consumer<Apple> consumer) {
        list.forEach(consumer);
//        list.forEach(consumer::accept);
    }

    @Test
    public void testConsumer() {
        appleConsumer(appleList, System.out::println);
    }

    private void appleBiConsumer(List<Apple> list, String s, BiConsumer<Apple, String> consumer) {
        list.forEach(item -> consumer.accept(item, s));
    }

    @Test
    public void testBiConsumer() {
        appleBiConsumer(appleList, "str", (a, s) -> System.out.println(s + ":" + a));
    }
}
