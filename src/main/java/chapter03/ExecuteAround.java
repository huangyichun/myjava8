package chapter03;

import java.io.*;

/**
 * @author huangyichun
 * @date 2018/10/29
 *
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
    }

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return processor.process(br);
        }
    }
}
