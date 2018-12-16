package chapter07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author huangyichun
 * @date 2018/12/9
 */
public class ParallelStream {

    public static void main(String[] args) {
        System.out.println(sequentialSum(10));
        /**
         * 获取处理器默认线程数
         */
        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelStream::iterativeSum, 10000000) + "msecs");
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStream::sequentialSum, 10000000) + "msecs");
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStream::parallelSum, 10000000) + "msecs");

        System.out.println("ForkJoin sum done in:" + measureSumPerf(ParallelStream::forkJoinSum, 10000000) + "msecs");
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static long parallelSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }

    public static long sequentialSum (long n) {
//        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
        return LongStream.rangeClosed(1, n).reduce(0, Long::sum);
    }

    public static long iterativeSum(long n) {
        long sum = 0;
        for (long i = 1; i <= n; i ++ ) {
            sum += i;
        }
        return sum;
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1000000;
//            System.out.println("result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}
