import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MySever {

    public static ArrayList<Socket> socketList = new ArrayList<Socket>();
    public static String content="";

    public static void main(String[] args) throws IOException {
        //����ServerSocket
        ServerSocket ss = new ServerSocket(30003);

        //���Ͻ��մ˶˿ڵ�socket�����洢��socketList��
        //����Ϊÿһ��socket����һ���̣߳����ڽ�����Ϣ
        while (true) {
            Socket s = ss.accept();
            System.out.println("connect success!");
            socketList.add(s);

            new Thread(new ServerThread(s)).start();
        }
    }

}