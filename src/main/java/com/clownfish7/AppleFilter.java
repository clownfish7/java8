package com.clownfish7;

/**
 * @author yzy
 * @classname AppleFilter
 * @description TODO
 * @create 2020-04-09 7:38 PM
 */
@FunctionalInterface
public interface AppleFilter {
    boolean filter(Apple apple);

    default void print() {

    }
}
