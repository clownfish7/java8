package com.clownfish7.stream;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author You
 * @create 2020-04-11 16:50
 */
public class StreamCreatTest {

    @Test
    public void testStreamCreateFromCollection() {
        List<String> list = Arrays.asList("hello", "alex", "clownfish7", "world", "stream");
        Stream<String> stream = list.stream();
    }

    @Test
    public void testStreamCreateFromValues() {
        Stream<String> stream = Stream.of("hello", "alex", "clownfish7", "world", "stream");
    }

    @Test
    public void testStreamCreateFromArrays() {
        String[] strings = {"hello", "alex", "clownfish7", "world", "stream"};
        Stream<String> stream = Arrays.stream(strings);
    }

    @Test
    public void testStreamCreateFromFile() throws IOException {
        Path path = Paths.get("D:\\you\\projects\\java8\\src\\main\\java\\com\\clownfish7\\Apple.java");
        try (Stream<String> streamFromFile = Files.lines(path)) {
            streamFromFile.forEach(System.out::println);
        }
    }

    @Test
    public void testStreamCreateFromIterator() {
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
    }

    @Test
    public void testStreamCreateFromGenerate() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    public void testObjStreamCreateFromGenerator() {
        Stream.generate(() -> new Obj(1, "2")).limit(10).forEach(System.out::println);
    }

    class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
