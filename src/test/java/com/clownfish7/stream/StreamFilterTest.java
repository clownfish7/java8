package com.clownfish7.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author You
 * @create 2020-04-11 17:19
 */
public class StreamFilterTest {

    private List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6,6,8,8);

    @Test
    public void testStreamFilter() {
        list.stream().filter(i -> i % 2 == 0).forEach(System.out::println);
    }

    @Test
    public void testStreamDistinct() {
        list.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void testStreamLimit() {
        list.stream().limit(5).forEach(System.out::println);
    }

    @Test
    public void testStreamSkip() {
        list.stream().skip(5).forEach(System.out::println);
    }
}
