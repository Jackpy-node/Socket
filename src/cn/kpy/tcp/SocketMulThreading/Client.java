package cn.kpy.tcp.SocketMulThreading;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            //1、创建客户端socket，指定服务器IP和Port
            Socket socket = new Socket("127.0.0.1", 8888);
            //2、获取输出流，向服务器发送数据
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream SendToServer = new DataOutputStream(outputStream);
            SendToServer.writeUTF("Hello Server, I'm Client");
            socket.shutdownOutput();
            //3、获取输入流，读取服务器返回数据
            InputStream inputStream = socket.getInputStream();
            DataInputStream ReceiveFromServer = new DataInputStream(inputStream);
            System.out.println("Server Response：" + ReceiveFromServer.readUTF());
            socket.close();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
