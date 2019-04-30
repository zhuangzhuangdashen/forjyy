package sockettest;

import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/4.
 */
public class SocketTest {
    public static void main(String[] args) {

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost.getAddress().toString());
            System.out.println(localHost.getHostName());
            System.out.println(localHost.getHostAddress());

            System.out.println(SocketTest.class.getClassLoader());

            System.out.println(SocketTest.class.getClassLoader().getParent());

            System.out.println(SocketTest.class.getClassLoader().getParent().getParent());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
