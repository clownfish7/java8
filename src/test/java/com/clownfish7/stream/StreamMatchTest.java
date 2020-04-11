package com.clownfish7.stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author You
 * @create 2020-04-11 20:25
 */
public class StreamMatchTest {

    public final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

    @Test
    public void testStreamAllMatch() {
        boolean result = stream.allMatch(i -> i > 0);
        assert result : "err";
    }

    @Test
    public void testStreamAnyMatch() {
        boolean result = stream.anyMatch(i -> i > 4);
        assert result : "err";
    }

    @Test
    public void testStreamNoneMatch() {
        boolean result = stream.noneMatch(i -> i > 5);
        assert result : "err";
    }
}
