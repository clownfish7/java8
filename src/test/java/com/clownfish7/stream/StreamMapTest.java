package com.clownfish7.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author You
 * @create 2020-04-11 17:19
 */
public class StreamMapTest {

    private List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 8, 8);

    @Test
    public void testStreamMap() {
        list.stream().map(i -> i + 1).forEach(System.out::println);
    }

    @Test
    public void testStreamFlatMap() {
        List<String> list = Arrays.asList("Hello", "World");
        list.stream()
                .map(c->c.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
    }

}
