package chapter02;

import chapter01.Apple;

/**
 * @author huangyichun
 * @date 2018/10/27
 */
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
