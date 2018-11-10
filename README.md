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

## 函数式接口
函数式接口就是只定义一个抽象方法的接口

###函数描述符
函数式接口的抽象方法的签名称为函数描述符。

函数式接口的抽象方法的签名基本上就是Lambda表达式的签名，我们将这种抽象方法叫做函数描述符。
例如：Runnable接口可以看作一个什么也不接受什么也不返回的函数签名 
具体签名如下：
1. () -> void 
2.(Apple, Apple) -> int 

Java8定义了多个函数式接口
1. Predicate (T t) -> boolean
2. Consumer  (T t) -> void
3  ...

## 构建方法引用
三类：
1. 指向静态方法的方法引用
String s -> Integer.parseInt(s) ->  Integer::parseInt

2. 指向任意类型示例方法的方法引用
String s -> s.length() -> String::length

3.指向现有对象的实例方法的方法引用
(args) -> expr.instanceMethod(args) -> expr::instanceMethod



