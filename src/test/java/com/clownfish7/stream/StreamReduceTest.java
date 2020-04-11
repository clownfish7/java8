package com.clownfish7.stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author You
 * @create 2020-04-11 20:45
 */
public class StreamReduceTest {

    public final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

    @Test
    public void testStreamReduceSum() {
        stream.reduce(Integer::sum).ifPresent(System.out::println);
    }

    @Test
    public void testStreamReduceMax() {
        stream.reduce(Integer::max).ifPresent(System.out::println);
    }

    @Test
    public void testStreamReduceMin() {
        stream.reduce(Integer::min).ifPresent(System.out::println);
    }
}
