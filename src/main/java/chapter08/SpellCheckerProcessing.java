package chapter08;

/**
 * @author huangyichun
 * @date 2019/1/4
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}
