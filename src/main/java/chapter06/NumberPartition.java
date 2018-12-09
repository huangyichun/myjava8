package chapter06;

import chapter02.Predicate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author huangyichun
 * @date 2018/12/6
 */
public class NumberPartition {

    public static void main(String[] args) {
        NumberPartition numberPartition = new NumberPartition();
        System.out.println(numberPartition.isPrime(4));
        Map<Boolean, List<Integer>> partition1 = numberPartition.partitionPrimes(20);
        System.out.println(partition1);

        Map<Boolean, List<Integer>> partition2 = numberPartition.partitionPrimesWithCustomCollector(20);
        System.out.println(partition2);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(this::isPrime));
    }

    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

}
