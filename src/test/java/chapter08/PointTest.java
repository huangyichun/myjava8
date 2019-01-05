package chapter08;

import chapter02.Filter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangyichun
 * @date 2019/1/4
 */
public class PointTest {

    @Test
    public void testMoveRightBy() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        Assert.assertEquals(15, p2.getX());
        Assert.assertEquals(5, p2.getY());
    }

    @Test
    public void testComparingTwoPoints() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void  testMoveAllPointsRightBy() {
        List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));

        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
        Assert.assertEquals(expectedPoints, newPoints);
    }

    @Test
    public void testFilter() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = Filter.filter(numbers, i -> i % 2 == 0);
        List<Integer> smallerThanThree = Filter.filter(numbers, i -> i < 3);

        Assert.assertEquals(Arrays.asList(2, 4), even);
        Assert.assertEquals(Arrays.asList(1, 2), smallerThanThree);
    }

    @Test
    public void testForEach() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
                .map(x -> x + 17)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println);

        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter:" + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(Collectors.toList());

    }

}
