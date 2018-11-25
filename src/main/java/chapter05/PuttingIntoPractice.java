package chapter05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * @author huangyichun
 * @date 2018/11/25
 */
public class PuttingIntoPractice {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /**
         * 找出2011年发生的所有交易，并按交易额排序
         */
        List<Transaction> transactions1 = transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(transactions1);

        /**
         * 交易员都在哪些不同的城市工作过
         */
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        /**
         * 查找所有来自于剑桥的交易员，并按名字排序
         */
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traders);

        /**
         * 返回所有交易员的姓名字符串，并按字母顺序排序
         */
        String names = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
//                .reduce("", (x1, x2) -> x1 + x2 + " ");
        System.out.println(names);

        /**
         * 有没有交易员是在米兰工作的
         */
        boolean isMilan = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println(isMilan);

        /**
         * 打印生活在剑桥的交易员的所有交易额
         */
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        /**
         * 交易中最高的交易额
         */
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max).ifPresent(System.out::println);


        /**
         * 交易中最低的交易额
         */
         Optional<Transaction> min = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1);
         min.ifPresent(System.out::println);

         Optional<Transaction> transaction = transactions.stream()
                 .min(Comparator.comparing(Transaction::getValue));
         transaction.ifPresent(System.out::println);
    }
}
