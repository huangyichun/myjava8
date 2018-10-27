package chapter02;

import chapter01.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangyichun
 * @date 2018/10/18
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(100, "red"),
                new Apple(151, "red"),
                new Apple(88, "green"),
                new Apple(190, "green"));

        /**
         * 策略模式，实现多种行为一个参数（行为参数化）
         */
        List<Apple> result = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(result);

        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());

        /**
         * 使用匿名类
         */
        List<Apple> redApple = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        System.out.println(redApple);

        /**
         * Lambda表达式
         */
        List<Apple> lambdaResult = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(lambdaResult);


    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (flag && apple.getColor().equals(color)) {
                result.add(apple);
            } else if ((!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }
}
