package chapter08;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author huangyichun
 * @date 2019/1/4
 */
public class Main {

    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HandlerTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        /**
         * Lambda表达式替换
         */
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        System.out.println(pipeline.apply("Aren't labdas really sexy?!!"));
    }
}
