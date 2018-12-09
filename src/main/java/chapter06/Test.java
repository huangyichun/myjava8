package chapter06;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static chapter06.Dish.menu;

/**
 * @author huangyichun
 * @create 2018-12-03
 */
public class Test {

    public static void main(String[] args) {
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));

        mostCalorieDish.ifPresent(System.out::println);

        int totalCalories = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);

        //有问题
//        String shortMenu1 = Dish.menu.stream().collect(joining());
//        System.out.println(shortMenu1);

        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu2);

        System.out.println("test");

        /**
         * ToListCollector使用
         */
        List<Dish> dishes = menu.stream().collect(new ToListCollector<>());
        System.out.println(dishes);

        List<Dish> dishes1 = menu.stream().collect(ArrayList::new,
                List::add, List::addAll);
    }
}
