package chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * @author huangyichun
 * @date 2018/12/30
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2018, 12, 30);

        int year = date.getYear();
        System.out.println("年份 year=" + year);

        Month month = date.getMonth();
        System.out.println("月份 month=" + month);

        int day = date.getDayOfMonth();
        System.out.println("日期几 day=" + day);

        DayOfWeek dow = date.getDayOfWeek();
        System.out.println("星期 dow=" + dow);

        int len = date.lengthOfMonth();
        System.out.println("本月天数 len=" + len);

        boolean leap = date.isLeapYear();
        System.out.println("是否是闰年 leap=" + leap);

        /**
         * 获取当前时间
         */
        LocalDate today = LocalDate.now();
        System.out.println("当前时间:" + today);

        /**
         * 使用Parse方法解析一个DateTimeFormatter
         */
        LocalDate localDate = LocalDate.parse("2019-01-01");
        System.out.println(localDate);

        /**
         * 传递TemporalField参数读取时间
         */
        int year2 = date.get(ChronoField.YEAR);
        System.out.println("年份 year2=" + year2);

        int month2 = date.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("月份 month2=" + month2);

        int day2 = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("日期几 day2=" + day2);
    }
}
