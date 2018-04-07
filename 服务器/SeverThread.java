import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

public class ServerThread implements Runnable {

    private Socket mSocket = null;
    private BufferedReader mBufferedReader = null;

    // ���캯���н���socket���ҳ�ʼ��BufferedReader
    public ServerThread(Socket socket) 
            throws UnsupportedEncodingException, IOException {
        mSocket = socket;
        mBufferedReader = new BufferedReader(
                new InputStreamReader(mSocket.getInputStream(), "utf-8"));
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        try {
            String content = null;

            //ѭ���������Դ˿ͻ��˵���Ϣ
            //������ղ����ˣ������Ѿ��Ͽ����ͽ��˿ͻ��˴�socketList���Ƴ�
            while ((content = mBufferedReader.readLine()) != null) {

                System.out.println(content);

                //�������е�ÿ���ͻ��˷�������
                //����쳣��˵���Ͽ����ͽ�����Ŀ��socketList���Ƴ�
                for (Iterator<Socket> it = MySever.socketList.iterator(); 
                        it.hasNext();) {
                    Socket s = it.next();
                    try {
                        OutputStream os = s.getOutputStream();
                        os.write((content + "\n").getBytes("utf-8"));
                    } catch (SocketException e) {
                        e.printStackTrace();
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            MySever.socketList.remove(mSocket);
        }
    }

}
