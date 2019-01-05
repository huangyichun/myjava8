package chapter09;

import chapter06.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author huangyichun
 * @date 2019/1/1
 */
public class LambdaTest {

    public static void main(String[] args) {
        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel =
                Dish.menu.stream()
                    .collect(Collectors.groupingBy(
                            dish -> {
                                if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700)  return Dish.CaloricLevel.NORMAL;
                                else return Dish.CaloricLevel.FAT;
                            }));
        System.out.println(dishesByCaloricLevel);

        Map<Dish.CaloricLevel, List<Dish>> d2 = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getCaloricLevel));
        System.out.println(d2);

        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
    }
}
