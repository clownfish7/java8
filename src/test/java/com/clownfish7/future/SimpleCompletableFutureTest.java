package com.clownfish7.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yzy
 * @classname FutureTest
 * @description TODO
 * @create 2020-04-13 10:04 AM
 */
public class SimpleCompletableFutureTest {

    @Test
    public void testCompltable() throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return "finished";
            } catch (Exception e) {
                return "err";
            }
        });
        future.setCompltable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("err");
                cause.printStackTrace();
            }
        });
        System.out.println(1);
        System.out.println(2);
        System.out.println(future.get());
        TimeUnit.SECONDS.sleep(10);
    }

    private <T> T block(Callable<T> callable) {
        return callable.action();
    }

    private <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);

        Future<T> future = new Future<T>() {
            private Completable<T> completable;

            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public void setCompltable(Completable<T> compltable) {
                this.completable = compltable;
            }

            @Override
            public Completable<T> getCompltable() {
                return completable;
            }
        };

        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                if (future.getCompltable() != null) {
                    future.getCompltable().complete(value);
                }
            } catch (Exception e) {
                if (future.getCompltable() != null) {
                    future.getCompltable().exception(e);
                }
            }
        });
        t.start();

        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();

        void setCompltable(Completable<T> compltable);

        Completable<T> getCompltable();
    }

    private interface Callable<T> {
        T action();
    }

    private interface Completable<T> {
        void complete(T t);

        void exception(Throwable cause);
    }

}
