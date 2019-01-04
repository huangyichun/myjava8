package chapter12;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

/**
 * @author huangyichun
 * @date 2018/12/30
 */
public class InstantTest {

    public static void main(String[] args) {
        /**
         * 计算0 加到1000000和的时间
         */
        Instant start = Instant.now();

        int sum = IntStream.rangeClosed(0, 1000000).sum();
        System.out.println(sum);

        Duration duration = Duration.between(start, Instant.now());
        System.out.println(duration.toMillis());
    }
}
