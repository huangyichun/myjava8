package chapter12;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @author huangyichun
 * @date 2018/12/30
 */
public class PeriodTest {
    public static void main(String[] args) {
        Period period = Period.between(LocalDate.of(2017, 12, 1),
                LocalDate.of(2018, 3, 20));
        System.out.println(period.get(ChronoUnit.MONTHS));
    }
}
