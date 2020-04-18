package com.clownfish7.future;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yzy
 * @classname CompletableFutureTest
 * @description TODO
 * @create 2020-04-13 1:19 PM
 */
public class CompletableFutureTest {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    @Test
    public void test() throws InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        }).start();
        System.out.println("----- no blocking -----");
        completableFuture.whenComplete((v, t) -> {
            Optional.of(v).ifPresent(System.out::println);
            Optional.of(t).ifPresent(Throwable::printStackTrace);
        });
        wait5();
    }

    private double get() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = RANDOM.nextDouble();
        System.out.println(result);
        return result;
    }

    @Test
    public void testSupplyAsync() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });
        System.out.println("----- no blocking ----- ");
        CompletableFuture.supplyAsync(this::get, executorService)
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                    Optional.of(t).ifPresent(Throwable::printStackTrace);
                });
        wait5();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });
        System.out.println("----- no blocking ----- ");
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double result = RANDOM.nextDouble();
            System.out.println(result);
            return result;
        }, executorService)
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                    Optional.of(t).ifPresent(Throwable::printStackTrace);
                });
        System.out.println("----- no blocking ----- 1");
        System.out.println("----- no blocking ----- 2");
        System.out.println("----- no blocking ----- 3");
    }

    @Test
    public void testThenApply() throws InterruptedException {
        CompletableFuture.supplyAsync(this::get)
                .thenApply(d -> d * 10)
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                });
        wait5();
    }

    @Test
    public void testThenApplyPro() throws InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = list.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> i + 1, Executors.newFixedThreadPool(5)))
                .map(future -> future.thenApply(i -> i * 10))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        Optional.of(result).ifPresent(System.out::println);
    }

    private void wait5() throws InterruptedException {
        System.out.println("----- no blocking ----- 1");
        System.out.println("----- no blocking ----- 2");
        System.out.println("----- no blocking ----- 3");
        TimeUnit.SECONDS.sleep(5);
    }
}
