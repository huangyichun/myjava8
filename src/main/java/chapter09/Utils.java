package chapter09;

import java.util.List;

/**
 * @author huangyichun
 * @date 2019/1/5
 */
public class Utils {

    public static void paint(List<Resizable> list) {
        list.forEach(r -> {
            r.setAbsoluteSize(42, 42);
            r.draw();
        });
    }
}
