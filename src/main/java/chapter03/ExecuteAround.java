package chapter03;

import java.io.*;
import java.util.function.Function;

/**
 * @author huangyichun
 * @date 2018/10/29
 * <p>
 * 1.行为参数化 （BufferedReaderProcessor接口创建)
 * 2.使用函数式接口来传递行为 (processFile方法参数设置为BufferedReaderProcessor)
 * 3.执行一个行为(processFile方法的实现修改为bufferedReaderProcessor.process(br)
 * 4.传递Lambda （main方法中）
 */
public class ExecuteAround {


    private static String filePath = System.getProperty("user.dir") + "/src/main/java/chapter03/LambdaTest.java";

    public static void main(String[] args) throws IOException {
        String result = processFile((BufferedReader reader) -> reader.readLine() + reader.readLine());
        System.out.println(result);

        result = processFile((BufferedReader reader) -> reader.readLine() + reader.readLine() + reader.readLine());
        System.out.println(result);

        /**
         * br.readLine()会产生受检异常，IOException，
         * Lambda有两种方式处理：
         * 1. 定义一个自己的函数接口，并声明受检异常，
         * 2. 把Lambda包在一个try/catch块中
         */
        result = processFileByFunction((BufferedReader reader) -> {
            try {
                return reader.readLine() + reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(result);
    }

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return processor.process(br);
        }
    }

    public static String processFileByFunction(Function<BufferedReader, String> function) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return function.apply(br);
        }
    }
}
