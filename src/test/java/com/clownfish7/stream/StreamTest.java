package com.clownfish7.stream;

import com.clownfish7.Dish;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author You
 * @create 2020-04-11 15:25
 */
public class StreamTest {

    private List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void testLowGetDishNamesByCollections() {
        List<Dish> lowCalories = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400)
                lowCalories.add(d);
        }
        Collections.sort(lowCalories, (d1, d2) -> d1.getCalories() - d2.getCalories());
        List<String> dishNameList = new ArrayList<>();
        for (Dish d : lowCalories) {
            dishNameList.add(d.getName());
        }
        System.out.println(dishNameList);
    }

    @Test
    public void test() {
        long l = System.currentTimeMillis();
        List<String> collect = menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(collect + " " + (System.currentTimeMillis() - l));
    }
}
