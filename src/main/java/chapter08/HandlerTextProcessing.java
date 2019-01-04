package chapter08;

/**
 * @author huangyichun
 * @date 2019/1/4
 */
public class HandlerTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }
}
