package chapter03;

import chapter01.Apple;

import java.util.Comparator;

/**
 * @author huangyichun
 * @date 2018/10/28
 */
public class LambdaTest {

    public static void main(String[] args) {
        /**
         * 第一个Lambda表达式
         */
       Comparator<Apple> byWeight = new Comparator<Apple>() {
           @Override
           public int compare(Apple a1, Apple a2) {
               return a1.getWeight().compareTo(a2.getWeight());
           }
       };

       Comparator<Apple> byLambdaWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

    }
}
