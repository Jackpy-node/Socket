package cn.kpy.tcp.SocketMulThreading;

import java.io.*;
import java.net.Socket;

/**
 *服务器线程处理类
 **/
public class ServerThread extends Thread{

    //和本线程相关的socket
    Socket socket;
    public ServerThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //获取服务端请求数据流
            inputStream = socket.getInputStream();
            DataInputStream ReceiveFromClient = new DataInputStream(inputStream);
            System.out.println("Client Request：" + ReceiveFromClient.readUTF());
            socket.shutdownInput();

            //返回数据流给客户端
            outputStream = socket.getOutputStream();
            DataOutputStream SendToClient = new DataOutputStream(outputStream);
            SendToClient.writeUTF("Hello Client !!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
