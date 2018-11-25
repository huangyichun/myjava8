package chapter04;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author huangyichun
 * @date 2018/11/11
 */
public class StreamVsCollection {

    public static void main(String[] args) {
        /**
         * 集合处理
         */
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : Dish.menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        lowCaloricDishes.sort(Comparator.comparing(Dish::getCalories));
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        System.out.println(lowCaloricDishesName);

        /**
         * Java 8 流处理
         */
        List<String> streamLowCaloricDishesName =
                Dish.menu.parallelStream()
                        .filter(d -> d.getCalories() < 400)
                        .sorted(Comparator.comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(Collectors.toList());
        System.out.println(streamLowCaloricDishesName);

        List<String> threeHighCaloricDishNames = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);

        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);

        System.out.println(title.stream().map(String::length).collect(Collectors.toList()));

        System.out.println("------------------------------------------");
        List<String> names = Dish.menu.parallelStream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                }).map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                }).limit(3).collect(Collectors.toList());

        System.out.println(names);
        System.out.println("-----------------------------------------");

        List<String> words = Arrays.asList("Java8", "In", "Action");
        List<String> list = words.stream().map(d -> d.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);

        String[] strings = "Action".split("");
        for (String str : strings) {
            System.out.println(str);
        }

        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        number1.stream()
                .flatMap(i ->
                        number2.stream().filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})

                ).forEach(
                x -> {
                    for (int i : x) {
                        System.out.print(i);
                    }
                    System.out.println();
                }
        );

        /**
         * Optional使用
         */
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        /**
         * 规约，求累加
         */
        List<Integer> numbers = Arrays.asList(4, 5, 9, 1);
       /* int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum = numbers.stream().reduce(0, (a, b) -> Integer.sum(a, b));*/
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum=" + sum);

        /**
         * 求最大值
         */
        int max = numbers.stream().reduce(0, Integer::max);
        System.out.println("max=" + max);

        Optional<Integer> max2 = numbers.stream().reduce(Integer::max);
        max2.ifPresent(System.out::println);

        /**
         * 计算menu中有几道菜
         */
        int count = Dish.menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);

        long count2 = Dish.menu.stream().count();

        System.out.println("count=" + count);
        System.out.println("count2=" + count2);

    }
}