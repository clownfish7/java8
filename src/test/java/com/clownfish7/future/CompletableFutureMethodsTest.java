package com.clownfish7.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author You
 * @create 2020-04-17 23:28
 */
public class CompletableFutureMethodsTest {

    @Test
    public void test() {
        CompletableFuture.supplyAsync(() -> 2)
                .thenApply(i -> i * 10)
                .thenApply(i -> i * 10)
                .whenComplete((v, t) -> System.out.println(v));
        System.out.println("----------");
        CompletableFuture.supplyAsync(() -> 2)
                .thenApply(i -> i * 10)
                .thenApply(i -> i * 10)
                .whenCompleteAsync((v, t) -> System.out.println(v));
        System.out.println("1");
    }

    @Test
    public void testHandle() {
        CompletableFuture.supplyAsync(() -> 2)
                .thenApply(i -> i * 10)
                .handle((v, t) -> v + 1)
                .whenComplete((v, t) -> System.out.println(v));
        System.out.println("----------");
        CompletableFuture.supplyAsync(() -> 2)
                .thenApply(i -> i * 10)
                .handleAsync((v, t) -> v + 1)
                .whenComplete((v, t) -> System.out.println(v));
        System.out.println("1");
    }

    @Test
    public void testAccept() {
        CompletableFuture.supplyAsync(() -> 2)
                .thenApply(i -> i * 10)
                .thenAccept(System.out::println);
        System.out.println("----------");
        CompletableFuture.supplyAsync(() -> 2)
                .thenApply(i -> i * 10)
                .thenAcceptAsync(System.out::println)
                .thenRun(() -> System.out.println("clownfish"));
        System.out.println("1");
    }

    @Test
    public void testThenCompose() {
        CompletableFuture.supplyAsync(() -> 2)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 1))
                .thenAccept(System.out::println);
        System.out.println("----------");
        CompletableFuture.supplyAsync(() -> 2)
                .thenComposeAsync(i -> CompletableFuture.supplyAsync(() -> i + 1))
                .thenAcceptAsync(System.out::println)
                .thenRun(() -> System.out.println("clownfish"));
        System.out.println("1");
    }

    @Test
    public void testThenCombine() {
        CompletableFuture.supplyAsync(() -> 2)
                .thenCombine(CompletableFuture.supplyAsync(() -> 1), (v1, v2) -> v1 + v2)
                .thenAccept(System.out::println);
        System.out.println("----------");
        CompletableFuture.supplyAsync(() -> 2)
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> 1), (v1, v2) -> v1 + v2)
                .thenAcceptAsync(System.out::println)
                .thenRun(() -> System.out.println("clownfish"));
        System.out.println("1");
    }

    @Test
    public void testThenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2), (v1, v2) -> {
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(Thread.currentThread());
                }).thenRun(() -> System.out.println("clownfish7"));
        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> 2), (v1, v2) -> {
                    System.out.println(v1);
                    System.out.println(v2);
                    System.out.println(Thread.currentThread());
                }).thenRunAsync(() -> System.out.println("clownfish7"));
    }

    @Test
    public void testRunAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("running 1");
            waitSeconds(1);
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("running 2");
            waitSeconds(1);
            return 2;
        }), () -> System.out.println("over"));
        waitSeconds(5);
    }

    @Test
    public void testApplyToEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("running 1");
            waitSeconds(2);
            return 1;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("running 2");
            waitSeconds(1);
            return 2;
        }), i -> i * 10).thenAccept(System.out::println);
        waitSeconds(5);
    }

    @Test
    public void testAcceptEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("running 1");
            waitSeconds(1);
            return 1;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("running 2");
            waitSeconds(2);
            return 2;
        }), System.out::println);
        waitSeconds(5);
    }

    @Test
    public void testRunAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("running 1");
            waitSeconds(1);
            return 1;
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("running 2");
            waitSeconds(2);
            return 2;
        }), () -> System.out.println("over"));
        waitSeconds(5);
    }

    @Test
    public void testAnyOf() {
        CompletableFuture.anyOf(Stream.of(1, 2, 3, 4, 5)
                .map(i -> CompletableFuture.supplyAsync(() -> i)).toArray(CompletableFuture[]::new))
                .thenAccept(System.out::println);

        waitSeconds(5);
    }

    @Test
    public void testAllOf() {
        CompletableFuture.allOf(Stream.of(1, 2, 3, 4, 5)
                .map(i -> CompletableFuture.supplyAsync(() -> i)).toArray(CompletableFuture[]::new))
                .thenRun(() -> System.out.println("over"));

        waitSeconds(5);
    }

    private void waitSeconds(int seconds) {
        System.out.println("----- " + seconds + " -----");
        System.out.println("----- no blocking ----- 1");
        System.out.println("----- no blocking ----- 2");
        System.out.println("----- no blocking ----- 3");
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
