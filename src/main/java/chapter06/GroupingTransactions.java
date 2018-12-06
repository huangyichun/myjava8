package chapter06;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author huangyichun
 * @date 2018/11/26
 */
public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0) );

    public static void main(String ... args) {

        Map<Currency, List<Transaction>> map = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
        long howManyDished = Dish.menu.stream().collect(Collectors.counting());
        long howManyDished2 = Dish.menu.stream().count();
        System.out.println(howManyDished);
        System.out.println(howManyDished2);

        int totalCalories = Dish.menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        int totalCalories1 = Dish.menu.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j);
        System.out.println(totalCalories);

        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        Optional<Dish> mostCalorieDish1 = Dish.menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        mostCalorieDish.ifPresent(System.out::println);

        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();

        /**
         * 错误reduce用法
         */
        List<Integer> numbers = stream.reduce(new ArrayList<>(), (List<Integer> l, Integer e) ->
        {
            l.add(e);
            return l;
        }, (List<Integer> l1, List<Integer> l2) -> {
            l1.addAll(l2);
            return l1;
        });

        System.out.println(numbers);

        int totalCalories3 = Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories3);

        int totalCalories4 = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories4);

        /**
         * 分组
         */
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                })
        );
        System.out.println(dishesByCaloricLevel);

        /**
         * 二级分组
         */

        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy( dish -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        })
                )
        );
        System.out.println(dishesByTypeCaloricLevel);

        /**
         * 按子组收集数据
         */
        Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(
                groupingBy(Dish::getType, counting())
        );
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                Dish.menu.stream().collect(groupingBy(Dish::getType,
                        maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByType2 = Dish.menu.stream()
                .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get)));
        System.out.println(mostCaloricByType2);

        Map<Dish.Type, Integer> totalCaloricByType3 = Dish.menu.stream()
                .collect(groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        System.out.println(totalCaloricByType3);

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType = Dish.menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                    else if(dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                }, toSet())
        ));
        System.out.println(caloricLevelsByType);

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType2 = Dish.menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                    else if(dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                }, toCollection(HashSet::new)))
        );
        System.out.println(caloricLevelsByType2);

        Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes);

        List<Dish> vegetarianDishes1 = Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println(vegetarianDishes1);

        Map<Boolean,Map<Dish.Type, List<Dish>> > partitionedMenu2 = Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(partitionedMenu2);
    }

    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }
}
