package chapter02;

import chapter01.Apple;

/**
 * @author huangyichun
 * @date 2018/10/22
 */
public class AppleRedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor()) && apple.getWeight() > 150;
    }
}
