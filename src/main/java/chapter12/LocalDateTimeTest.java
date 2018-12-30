package chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author huangyichun
 * @date 2018/12/30
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 12, 30, 19, 3, 20);
        System.out.println(localDateTime);

        LocalDate date = LocalDate.of(2018, 12, 30);
        LocalTime time = LocalTime.of(19, 7, 20);
        LocalDateTime localDateTime1 = LocalDateTime.of(date, time);
        System.out.println(localDateTime1);

        LocalDate date1 = localDateTime1.toLocalDate();
        LocalTime time1 = localDateTime1.toLocalTime();
        System.out.println(date1 + " " + time1);
    }
}
