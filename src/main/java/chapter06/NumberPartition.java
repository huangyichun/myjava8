package chapter06;

import java.util.stream.IntStream;

/**
 * @author huangyichun
 * @date 2018/12/6
 */
public class NumberPartition {

    public static void main(String[] args) {
        System.out.println(isPrime(5));
    }

    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }
}
