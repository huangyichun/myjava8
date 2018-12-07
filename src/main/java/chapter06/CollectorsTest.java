package chapter06;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huangyichun
 * @date 2018/12/7
 */
public class CollectorsTest {

    public static void main(String[] args) {
        /**
         * 1.toList 把流中所有项目收集到一个List
         */
        List<Dish> dishes = Dish.menu.stream().collect(Collectors.toList());
        System.out.println(dishes);

        /**
         * 2. toSet 把流中所有项目收集到一个Set，删除重复项
         */
        Set<Dish> dishSet = Dish.menu.stream().collect(Collectors.toSet());
        System.out.println(dishSet);

        /**
         * 3.toCollection 把流中的所有项目收集到给定的供应源创建的集合
         */
        Collection<Dish> dishCollection = Dish.menu.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(dishCollection);

        /**
         * 4. counting 计算流中元素的个数
         */
        long howManyDishes = Dish.menu.stream().collect(Collectors.counting());
        System.out.println(howManyDishes);

        /**
         * 5. summingInt 对流中项目的一个整数属性求和
         */
        int totalCalories = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        /**
         * 6. averagingInt 计算流中项目Integer属性的平均值
         */
        double avgCalories = Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        /**
         * 7. summarizingInt 收集关于流中项目Integer属性的统计值，例如最大，最小，总，与平均值
         */
        IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        /**
         * 8. joining连接对流中每个项目调用toString方法所生成的字符串
         */
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(shortMenu);

        /**
         *  9. maxBy一个包裹了流中按照给定比较器选出的最大元素
         */
        Optional<Dish> fattest = Dish.menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(fattest);

        /**
         *  10. minBy一个包裹了流中按照给定比较器选出的最小元素
         */
        Optional<Dish> lightest = Dish.menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(lightest);

        /**
         *  11. reducing从一个作为累加器的初始值开始，利用 BinaryOperator 与流中的元素逐个结合，从而将流归约为单个值
         */
        int totalCalories2 = Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories2);

        /**
         *  12. collectingAndThen包裹另一个收集器，对其结果应用转换函数
         */
        int howManyDishes2 = Dish.menu.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(howManyDishes2);

        /**
         *  13. groupingBy 根据项目的一个属性的值对流中的项目作分组，并将属性值作为结果 Map 的键
         */
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        /**
         *  14. partitioningBy  Map<Boolean,List<T>> 根据对流中每个项目应用谓词的结果来对项目进行分区
         */
        Map<Boolean, List<Dish>> vegetarianDishes = Dish.menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(vegetarianDishes);
    }
}
