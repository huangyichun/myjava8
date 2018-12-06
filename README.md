# myjava8

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



### 函数描述符
函数式接口的抽象方法的签名称为函数描述符。

函数式接口的抽象方法的签名基本上就是Lambda表达式的签名，我们将这种抽象方法叫做函数描述符。
例如：Runnable接口可以看作一个什么也不接受什么也不返回的函数签名 
具体签名如下：
1. () -> void 
2. (Apple, Apple) -> int 

Java8定义了多个函数式接口
1. Predicate (T t) -> boolean
2. Consumer  (T t) -> void
3  ...

## 方法引用
   方法引用可以让你重复使用现有的方法定义，并像lambda一样传递，可以看作是一种lambda表达式的简写。
 
 例如：
    
   ```java
    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

    inventory.sort(comparing(Apple::getWeight)); //方法引用
```
      
    
三类：
1. 指向静态方法的方法引用
String s -> Integer.parseInt(s) ->  Integer::parseInt

2. 指向任意类型示例方法的方法引用
String s -> s.length() -> String::length

3. 指向现有对象的实例方法的方法引用
(args) -> expr.instanceMethod(args) -> expr::instanceMethod

## 流

定义：从支持数据处理操作的源生成的元素系列

元素系列：就像集合一样，流也提供一个接口，可以访问特定元素类型的一组有序值
源： 流会使用一个提供数据的源（从有序集合生成流时会保留原有的顺序）
数据处理操作：流的数据处理功能支持类型于数据库的操作，以及函数式变成语言中的常用操作(filter, map, reduce, find, match, sort等)
流水线： 很多操作本身会返回一个流，这样多个操作就可以链接起来，形成一个流水线
内部迭代：与使用迭代器显示迭代的集合不同，流的迭代操作是背后进行的。
```java
    List<String> threeHighCaloricDishNames = Dish.menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .map(Dish::getName)
                    .limit(3)
                    .collect(Collectors.toList());

```
- filter: 接受Lambda，从流中排除某些元素
- map: 接受一个Lambda，将元素转换成其他形式或提取信息
- limit: 截断流，使其元素不超过给定数量
- collect：将流转换成其他形式

### 使用流
 流的使用一般包括三件事：
 
 - 一个数据源来执行一个查询
 - 一个中间操作链，形成一条流的流水线
 - 一个终端操作，执行流水线，并能生成结果
 
 #### 中间操作
  重点介绍几个
  
 1. flatMap 流的扁平化
  
    flatMap方法能让你把一个流的每个值都换成另一个流，然后把所有的流连接起来成为一个流。
  例如：字符串数组String[] arrayOfWords = {"GoodBye", "World"}
  返回一个列表：["G", "o", "o", "d", "B", "y", "e", "W", "o", "r", "l", "d"]
  
  ```java
  List<String> list = Arrays.stream(arrayOfWords)
                  .map(word -> word.split(""))
                  .flatMap(t -> Arrays.stream(t))
                  .collect(Collectors.toList());

  //进一步简写成
  List<String> list = Arrays.stream(arrayOfWords)
                  .map(word -> word.split(""))
                  .flatMap(Arrays::stream)
                  .collect(Collectors.toList());
  
  //输出结果为：
  [G, o, o, d, B, y, e, W, o, r, l, d]
```


  


  
   



