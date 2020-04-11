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

    public Apple() {

    }

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

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
