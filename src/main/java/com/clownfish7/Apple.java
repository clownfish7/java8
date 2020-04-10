package com.clownfish7;

/**
 * @author yzy
 * @classname Apple
 * @description TODO
 * @create 2020-04-09 7:23 PM
 */
public class Apple {

    private String color;
    private long weight;

    public Apple(String color, long weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
