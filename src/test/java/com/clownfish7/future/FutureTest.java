package com.clownfish7.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author yzy
 * @classname FutureTest
 * @description TODO
 * @create 2020-04-13 11:27 AM
 */
public class FutureTest {

    @Test
    public void test() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println(1);
        Future<String> result = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return "finished";
            } catch (InterruptedException e) {
                return "err";
            }
        });
        System.out.println(2);
        try {
            System.out.println(result.get(2,TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(3);
        executorService.shutdown();
    }
}
