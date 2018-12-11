package chapter07;

import java.util.concurrent.RecursiveTask;

/**
 * @author huangyichun
 * @date 2018/12/10
 */
public class ForkJoinSumCalculator extends RecursiveTask {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computerSequentially();
        }
        /**
         * 创建一个任务为数组的前一半求和
         */
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        /**
         * 利用另一个ForkJoinPool线程异步执行新创建的子任务
         */
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = (Long) leftTask.join();
        return leftResult + rightResult;
    }

    private long computerSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
