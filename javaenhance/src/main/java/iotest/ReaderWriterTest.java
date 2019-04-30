package iotest;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/8.
 */
public class ReaderWriterTest {

    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("E:\\aaaaaa.java");
            writer.append("aaaaaaaaaaaaa");
            writer.flush();
            writer.append("bbbbbbbbbbbbb");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
