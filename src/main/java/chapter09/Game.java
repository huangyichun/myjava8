package chapter09;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangyichun
 * @date 2019/1/5
 */
public class Game {

    public static void main(String[] args) {
        List<Resizable> resizableShapes = Arrays.asList(new Square(), new Rectangle(), new Ellipse());
        Utils.paint(resizableShapes);
    }
}
