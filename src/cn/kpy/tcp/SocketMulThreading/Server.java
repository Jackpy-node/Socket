package cn.kpy.tcp.SocketMulThreading;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //1、创建服务器socket，即ServerSocket，指定绑定的Port，并监听该端口
            ServerSocket serverSocket=new ServerSocket(8888);
            //记录客户端数量
            int count=0;
            System.out.println("服务器即将启动，等待客户端连接......");
            //循环监听客户端连接
            while(true){
                //2、调用服务器端accept方法，监听客户端连接
                Socket socket=serverSocket.accept();
                //3、创建服务器线程
                ServerThread serverThread=new ServerThread(socket);
                //4、启动线程
                serverThread.start();
                //统计连接客户端数量
                count++;
                System.out.println("客户端数量："+count);
                InetAddress inetAddress=socket.getInetAddress();
                System.out.println("客户端IP："+inetAddress.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
