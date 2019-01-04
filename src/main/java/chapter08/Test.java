package chapter08;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangyichun
 * @date 2019/1/3
 */
public class Test {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("red", 10),
                new Apple("red", 10),
                new Apple("red", 11),
                new Apple("red", 12));
        apples.stream()
//                .map(Apple::getWeight)
                .distinct()
                .forEach(System.out::println);
    }


     static class Apple {

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        private String color;
        private int weight;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

         public int getWeight() {
             return weight;
         }

         public void setWeight(int weight) {
             this.weight = weight;
         }

         @Override
         public String toString() {
             return "Apple{" +
                     "color='" + color + '\'' +
                     ", weight=" + weight +
                     '}';
         }
     }
}
