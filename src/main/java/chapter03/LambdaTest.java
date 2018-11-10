package chapter03;

import chapter01.Apple;

import java.util.*;
import java.util.function.*;


/**
 * @author huangyichun
 * @date 2018/10/28
 */
public class LambdaTest<T> {

    private static int testNumber = 100;

    public static void main(String[] args) {
        /**
         * 第一个Lambda表达式
         */
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };

        Comparator<Apple> byLambdaWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        List<Integer> result = filter(Arrays.asList(1, 4, 5, 12, 21), (Integer i) -> i > 6);
        System.out.println(result);

        forEach(Arrays.asList("hello", "world", "I"), (String str) -> System.out.println(str));

        List<Integer> list = map(Arrays.asList("lambdas", "in", "action"), (String str) -> str.length());
        System.out.println(list);

        /**
         * 无装箱
         */
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        /**
         * 装箱（Integer 需要转换成int类型)
         */
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;

        Integer i = supplier(() -> 1000);
        System.out.println(i);

        Predicate<Integer> p = s -> list.add(s);
        Consumer<Integer> c = s -> list.add(s);

        /**
         * 使用局部变量必须为final类型，或者事实上是final
         */
        int portNumber = 1337;
        Runnable r1 = () -> System.out.println(portNumber);
        Runnable r2 = () -> System.out.println(testNumber);
        testNumber = 12323;

        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);

        Supplier<Apple> c1 = () -> new Apple();
        Apple a1 = c1.get();

        Supplier<Apple> c2 = Apple::new;
        Apple a2 = c2.get();

        BiFunction<Integer, String, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(123, "red");

        System.out.println("--------------------------------------");

        List<Integer> list1 = Arrays.asList(1, 3, 4, 5, 12);
        List<Apple> apples = getApples(list1, Apple::new);
        System.out.println(apples);

        System.out.println("---------------------------------------");
        Map<Integer, String> map = new HashMap<>();
        map.put(20, "red");
        map.put(100, "red");
        map.put(22, "black");
        map.put(21, "green");
        map.put(34, "yellow");
        map.put(35, "blue");
        List<Apple> appleList = getAppleList(map, Apple::new);
        appleList.add(new Apple(20, "black"));
        System.out.println(appleList);
        //第一种比较方法
//        appleList.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight().compareTo(o2.getWeight());
//            }
//        });
//        System.out.println(appleList);

        //第二种比较方法
        appleList.sort((Apple apple1, Apple apple2) -> apple1.getWeight().compareTo(apple2.getWeight()));
        appleList.sort((apple1, apple2) -> apple1.getWeight().compareTo(apple2.getWeight()));
        appleList.sort(Comparator.comparing((Apple a) -> a.getWeight()));
        appleList.sort(Comparator.comparing(a -> a.getWeight()));
        appleList.sort(Comparator.comparing(Apple::getWeight));


        appleList.sort(Comparator.comparing(Apple::getWeight)
                .thenComparing(Apple::getColor));
        System.out.println();
        System.out.println("--------------按重量正序，颜色正序排序--------------");
        System.out.println(appleList);

        appleList.sort(Comparator.comparing(Apple::getWeight).reversed()
                .thenComparing(Comparator.comparing(Apple::getColor).reversed()));
        System.out.println();
        System.out.println("--------------按重量逆序，颜色逆序排序--------------");
        System.out.println(appleList);

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);

        int result1 = h.apply(1);
        System.out.println(result1);

        Function<Integer, Integer> compose = f.compose(g);
        System.out.println(compose.apply(3));

        System.out.println();

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline =
                addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);

        System.out.println(transformationPipeline.apply("hello"));
    }

    public static List<Apple> getAppleList(Map<Integer, String> map, BiFunction<Integer, String, Apple> biFunction) {
        List<Apple> apples = new ArrayList<>();
        map.forEach((Integer i, String s) -> apples.add(biFunction.apply(i, s)));
        return apples;
    }

    public static List<Apple> getApples(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> apples = new ArrayList<>();
        for (Integer i : list) {
            apples.add(function.apply(i));
        }
        return apples;
    }

    private int test = 100;

    public void testPortNumber() {
        Runnable r2 = () -> System.out.println(test);
        test = 12323;
    }

    /**
     * 前缀<T>或<E>表示与类的<T>不相关，是一个新定义的类型
     * Predicate使用
     *
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Consumer接口使用
     *
     * @param list
     * @param consumer
     * @param <T>
     */
    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T i : list) {
            consumer.accept(i);
        }
    }

    /**
     * Function接口使用
     *
     * @param list
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(function.apply(s));
        }
        return result;
    }

    /**
     * Supplier接口 () -> T
     *
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T supplier(Supplier<T> supplier) {
        return supplier.get();
    }

}
