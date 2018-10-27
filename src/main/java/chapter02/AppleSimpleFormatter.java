package chapter02;

import chapter01.Apple;

/**
 * @author huangyichun
 * @date 2018/10/27
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + " g";
    }
}
