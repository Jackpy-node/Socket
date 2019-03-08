package cn.kpy.udp.DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread {
    private static final int MAX_LENGTH = 1024;//最大接收字节长度
    private static final int Port = 8088;//端口号
    private static byte[] receive_message = new byte[MAX_LENGTH];//存放接收数据字节数组

    public static void main(String[] args) {

        //数据报套接字
        DatagramSocket datagramSocket = null;
        //数据报接收对象
        DatagramPacket datagramPacket;

        try {
            /**
             * 接收数据
             * 1、开启客户端服务
             * 2、服务器端监听监听指定端口，当客户端向指定端口发送数据时，服务器端获取数据
             * 3、客户端只能通过与服务器端约定好的端口进行通信
             **/
            //建立UDP连接，监听相应端口
            datagramSocket = new DatagramSocket(Port);
            //未指定IP和Port,只能用于接收数据,准备空数据包接收数据
            datagramPacket = new DatagramPacket(receive_message, receive_message.length);
            //receive等待接收数据报
            datagramSocket.receive(datagramPacket);
            //解析数据包
            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println("服务器接收到的数据：" + message);

            /**
             * 发送数据
             * 1、当客户端通过与服务器约定的端口完成数据传输之后，服务器端可以获取远程客户端主机的IP和端口
             * 2、此时服务器作为数据发送方，将服务器端需要返回的数据通过远程客户端主机开启的端口进行返回
             * 3、由于客户端发送数据时也启动了一个端口，所以服务器端直接通过该端口将数据返回
             **/
            //回发客户端Port
            int Port_Client = datagramPacket.getPort();
            System.out.println("服务端返回数据Port："+Port_Client);
            //回发客户端IP
            InetAddress inetAddress=datagramPacket.getAddress();
            System.out.println("服务端返回数据IP："+inetAddress);
            //组装数据报
            byte[] buff = "I'm Server!!!".getBytes();
            //设置发送数据包,指定目标主机IP和Port
            datagramPacket = new DatagramPacket(buff, buff.length, inetAddress, Port_Client);
            //发送数据
            datagramSocket.send(datagramPacket);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
