package com.clownfish7.future;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        System.out.println("----- no blocking ----- 1");
        System.out.println("----- no blocking ----- 2");
        System.out.println("----- no blocking ----- 3");
        TimeUnit.SECONDS.sleep(5);
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
        System.out.println("----- no blocking ----- 1");
        System.out.println("----- no blocking ----- 2");
        System.out.println("----- no blocking ----- 3");
        TimeUnit.SECONDS.sleep(5);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });
        System.out.println("----- no blocking ----- ");
        CompletableFuture.supplyAsync(()->{
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
}
