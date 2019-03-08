package cn.kpy.udp.DatagramSocket;

import java.io.IOException;
import java.net.*;

public class Client {
    private static String send_message = "Hello Server!!!";
    private static String Address = "127.0.0.1";
    private static final int Port = 8088;

    public static void main(String[] args) {
        DatagramSocket datagramSocket=null;
        DatagramPacket datagramPacket;

        try {
            /**
             * 发送数据
             **/
            //建立UDP连接
            datagramSocket = new DatagramSocket();
            //创建发送数据字节数组
            byte[] buff = send_message.getBytes();
            //获取请求主机IP
            InetAddress inetAddress = InetAddress.getByName(Address);
            //设置发送数据包
            datagramPacket = new DatagramPacket(buff, buff.length, inetAddress, Port);
            //send方法发送数据
            datagramSocket.send(datagramPacket);


            /**
             * 接收数据
             **/
            byte[] receive_buff = new byte[1024];
            //准备空数据包接收数据
            datagramPacket = new DatagramPacket(receive_buff, receive_buff.length);
            //线程睡眠100秒，验证客户端通信时是否开启对应端口进行通信请求
            Thread.sleep(10000);
            //接收数据
            datagramSocket.receive(datagramPacket);
            //服务器通过获取远程客户端主机的IP和Port,将信息返回到客户端主机对应端口
            System.out.println(datagramPacket.getAddress()+"..."+datagramSocket.getLocalPort());
            //解析数据
            String receive_message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println("服务器返回的数据：" + receive_message);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            if(datagramSocket!=null){
                datagramSocket.close();
            }
        }
    }
}
