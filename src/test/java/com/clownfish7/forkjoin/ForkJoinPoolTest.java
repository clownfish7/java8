package com.clownfish7.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {

    private int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Test
    public void test() {
        System.out.println("result=> " + calc());

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        Integer result = forkJoinPool.invoke(task);
        System.out.println("AccumulatorRecursiveTask >>" + result);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println("AccumulatorRecursiveAction >>" + AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }

    private int calc() {
        int result = 0;
        for (int datum : data) {
            result += datum;
        }
        return result;
    }

}