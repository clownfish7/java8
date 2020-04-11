package com.clownfish7.stream;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author You
 * @create 2020-04-11 20:37
 */
public class StreamFindTest {

    public final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

    @Test
    public void testStreamFindAny() {
        Optional<Integer> optional = stream.findAny();
        optional.ifPresent(System.out::println);
    }

    @Test
    public void testStreamFindFirst() {
        Optional<Integer> optional = stream.findFirst();
        optional.ifPresent(System.out::println);
    }

}
