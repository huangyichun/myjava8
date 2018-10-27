package chapter02;

import chapter01.Apple;

/**
 * @author huangyichun
 * @date 2018/10/18
 */
public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
