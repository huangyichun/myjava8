package chapter03;

import java.io.*;

/**
 * @author huangyichun
 * @date 2018/10/29
 */
public class ExecuteAround {


    private static String filePath = System.getProperty("user.dir") + "/src/main/java/chapter03/LambdaTest.java";

    public static void main(String[] args) throws IOException {
        String result = processFile();
        System.out.println(result);
    }

    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        }
    }
}
