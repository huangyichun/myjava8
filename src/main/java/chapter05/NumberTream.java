package chapter05;

import chapter04.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author huangyichun
 * @date 2018/11/25
 */
public class NumberTream {

    public static void main(String[] args) {

        /**
         * 映射到数值流
         */
        int calories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);

        IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        OptionalInt maxCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);
        System.out.println(max);

        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        /**
         * IntStream中map只能为流中的每个元素返回一个int数组，
         * new int[]{a, b, (int)Math.sqrt(a * a + b * b) 要求返回一个int[]数组类型
         * 因此用boxed()或者mapToObj()
         */
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(1, 100)
                        .mapToObj(b -> new double[] {a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);
        pythagoreanTriples2.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        /**
         * 读取文件流
         */
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("src/main/java/chapter05/data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(uniqueWords);

        /**
         * 创建无限流
         */
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        IntStream ones = IntStream.generate(() -> 1);
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
