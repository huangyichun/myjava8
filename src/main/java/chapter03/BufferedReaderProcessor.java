package chapter03;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author huangyichun
 * @date 2018/10/29
 *
 * BufferedReader -> String 签名的接口
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    /**
     * lambda表达式接口
     * @param reader
     * @return
     * @throws IOException
     */
    String process(BufferedReader reader) throws IOException;
}
