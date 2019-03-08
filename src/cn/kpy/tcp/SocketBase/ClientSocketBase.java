package cn.kpy.tcp.SocketBase;

import java.io.*;
import java.net.Socket;

public class ClientSocketBase {
    public static void main(String[] args) {
        try {
            String ip = "127.0.0.1";
            int port = 8001;
            System.out.println("连接服务器：" + ip + "端口：" + port);
            //客户端请求主机IP和Port
            Socket client = new Socket(ip, port);
            //获取本地IP
            System.out.println("本地IP：" + client.getLocalSocketAddress());
            //远程连接主机IP
            System.out.println("服务器地址：" + client.getRemoteSocketAddress());
            //客户端向服务器请求数据
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("Hello Server!");
            //客户端接收服务器返回数据
            InputStream inputStream = client.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println("服务器返回数据：" + dataInputStream.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
