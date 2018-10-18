package chapter02;

import chapter01.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyichun
 * @date 2018/10/18
 */
public class FilteringApples {

    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}
