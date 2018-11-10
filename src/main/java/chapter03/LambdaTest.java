package chapter03;

import chapter01.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
    }

    private int test = 100;
    public void testPortNumber(){
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
    public static  <T> List<T> filter(List<T> list, Predicate<T> predicate) {
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
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T supplier(Supplier<T> supplier) {
        return supplier.get();
    }

}
