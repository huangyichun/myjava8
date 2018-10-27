package chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyichun
 * @date 2018/10/27
 */
public class Filter<T> {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
