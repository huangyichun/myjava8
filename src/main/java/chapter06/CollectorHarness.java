package chapter06;

/**
 * @author huangyichun
 * @date 2018/12/9
 */
public class CollectorHarness {

    public static void main(String[] args) {
        NumberPartition numberPartition = new NumberPartition();
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
//            numberPartition.partitionPrimes(1000000); // 428
            numberPartition.partitionPrimesWithCustomCollector(1000000);
            long duration =  (System.nanoTime() - start) / 1000000; //324
            if (duration < fastest) fastest = duration;
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }
}
