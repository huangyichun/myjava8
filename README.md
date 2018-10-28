# myjava8
java8相关学习

## Lambda表达式：
可以理解为简洁地表示可传递的匿名函数的一种方式，它没有名称，但有参数列表，函数主体、返回类型，可能还有一个可抛出的异常列表

例1：
```java
/**
* Lambda表达式之前
*/
Comparator<Apple> byWeight = new Comparator<Appler>() {
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight);
    }
}

/**
* Lambda表达式之后
*/
Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

```

例2

```java
/**
* String类型参数，返回一个int
*/
(String s) -> s.length() 

(int x, inty) -> {
    System.out.println("result:");
    System.out.println("x+y");
}
```

### Lambda的基本语法

- 参数列表： Comparator中compare方法的参数，两个Apple
- 箭头： -> 把参数列表与Lambda主体分隔开
- Lambda主体： 比较两个Apple的重量。表达式就是Lambda的返回值

(parameters) -> expression 或 (parameters) -> {statements}

