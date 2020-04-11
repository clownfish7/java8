package com.clownfish7.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author You
 * @create 2020-04-11 21:00
 */
public class NumbericStreamTest {

    private List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void test() {
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());

        int a = 9;
        IntStream.rangeClosed(1, 1000)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> Arrays.toString(new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .forEach(System.out::println);

        IntStream.rangeClosed(1, 1000)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> Arrays.toString(new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .forEach(System.out::println);
    }

}
