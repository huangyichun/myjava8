package chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
    }
}