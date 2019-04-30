package iotest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/8.
 */
public class OutputStreamTest {
    public static void main(String[] args) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("E:\\Person.java");
            outputStream = new FileOutputStream("E:\\aaaaaa.java");
            int len = 0;
            // 有了它 是按照一定的字节大小批量读
            // 没有的话就是一个字节一个字节的读 效率低
            byte[] b = new byte[1024];
            while ((inputStream.read(b))!=-1){
                outputStream.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}