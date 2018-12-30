package chapter12;

import java.time.LocalTime;

/**
 * 该类的使用方法和LocalDate类似
 * @author huangyichun
 * @date 2018/12/30
 */
public class LocalTimeTest {

    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(18, 25, 59);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.println("hour=" + hour + ", minute=" + minute + ", second=" + second);

        /**
         * 使用Parse方法解析一个DateTimeFormatter
         */
        LocalTime time = LocalTime.parse("18:35:29");
        System.out.println(time);
    }
}
