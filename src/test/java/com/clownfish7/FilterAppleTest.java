package com.clownfish7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yzy
 * @classname FilterApple
 * @description TODO
 * @create 2020-04-09 7:26 PM
 */
public class FilterAppleTest {

    @Test
    public void test() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 100),
                new Apple("yellow", 100),
                new Apple("green", 100),
                new Apple("pink", 100)
        );
        List<Apple> result = appleList.stream()
                .filter(item -> item.getColor().equals("red"))
                .collect(Collectors.<Apple>toList());
        assert result.size() == 1;
        assert result.get(0).getColor().equals("red") : "haha";
    }

    @Test
    public void testFilterApple() {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("blue", 100),
                new Apple("yellow", 100),
                new Apple("green", 100),
                new Apple("pink", 100)
        );
        List<Apple> result = filterApple(appleList, apple -> apple.getColor().equals("red"));
        assert result.size() == 1;
        assert result.get(0).getColor().equals("red") : "haha";
    }

    private List<Apple> filterApple(List<Apple> list, AppleFilter appleFilter) {
        List<Apple> apples = new LinkedList<>();
        list.forEach(item -> {
            if (appleFilter.filter(item)) {
                apples.add(item);
            }
        });
        return apples;
    }

    @Test
    public void testThreadRun() {
        new Thread(() ->
                System.out.println("heihei")
        ).start();
    }
}
