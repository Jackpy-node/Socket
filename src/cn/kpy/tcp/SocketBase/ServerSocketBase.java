package cn.kpy.tcp.SocketBase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketBase extends Thread {
    private ServerSocket serverSocket;

    public ServerSocketBase(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    @Override
    public void run() {
        try {
            System.out.println("启动服务器端监听，等待远程连接，端口号：" + serverSocket.getLocalPort() + "...");
            //侦听相应端口的连接
            Socket server = serverSocket.accept();
            //获取本地IP地址
            System.out.println("本地IP：" + server.getLocalSocketAddress());
            //远程连接主机IP
            System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
            //服务器接受客户端请求数据
            DataInputStream dataInputStream = new DataInputStream(server.getInputStream());
            System.out.println("客户端请求数据：" + dataInputStream.readUTF());
            //服务器向客户端返回数据
            DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
            dataOutputStream.writeUTF("thanks for connecting" + server.getRemoteSocketAddress() + "\nGoodBye!");
            //关闭服务端连接
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new ServerSocketBase(8001);
            thread.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
