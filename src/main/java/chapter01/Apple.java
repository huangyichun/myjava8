package chapter01;

/**
 * @author huangyichun
 * @create 2018-10-15
 */
public class Apple {

    public Apple() {
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    private Integer weight = 0;
    private String color = "";

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
