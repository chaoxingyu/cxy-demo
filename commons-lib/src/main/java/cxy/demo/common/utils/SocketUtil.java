package cxy.demo.common.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;

public class SocketUtil {

    private static final Logger LOGGER = Logger.getLogger(SocketUtil.class);


    /**
     * Socket通讯
     *
     * @param ip    IP地址
     * @param port  端口
     * @param isUdp 是否使用UDP协议
     * @param msg   发送数据
     * @return 响应数据
     */
    public static String sendMsg(String ip, int port, boolean isUdp, String msg) {
        LOGGER.info("Socket通讯：请求地址[" + ip + ":" + port + "], " + "请求数据[" + msg + "]");
        // 端口号是一个16位的二进制数字，端口范围: 0-65535
        if (0 > port || 65535 < port) {
            LOGGER.error("通讯配置错误：Socket通讯时端口不在允许的范围内！");
            return null;
        }
        byte[] sendData = null;
        try {
            sendData = msg.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("请求数据转码异常：", e);
            return null;
        }
        String rspMsg = "";
        if (isUdp) {
            rspMsg = sendMsgWithUdp(ip, port, sendData);
        } else {
            rspMsg = sendMsgWithTcp(ip, port, sendData);
        }
        LOGGER.info("Socket通讯：请求地址[" + ip + ":" + port + "], " + "响应数据[" + msg + "]");
        return rspMsg;
    }

    /**
     * tcp协议
     *
     * @param ip       地址
     * @param port     端口
     * @param sendData 发送数据
     * @return 响应数据
     */
    private static String sendMsgWithTcp(String ip, int port, byte[] sendData) {
        Socket client = null;
        OutputStream os = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            // 1.创建socket连接
            client = new Socket(ip, port);
            // 设置socket调用InputStream读数据的超时时间，以毫秒为单位
            client.setSoTimeout(3000);
            // 2.向服务端发送消息
            os = client.getOutputStream();
            os.write(sendData);
            os.flush();
            client.shutdownOutput();
            // 3.读取服务端消息
            is = client.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            isr.close();
            is.close();
            os.close();
            client.close();
            return sb.toString();
        } catch (UnknownHostException e) {
            LOGGER.error("连接主机异常: ", e);
        } catch (IOException e) {
            LOGGER.error("读取数据异常: ", e);
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != isr) {
                    isr.close();
                }
                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                }
                if (null != client) {
                    client.close();
                }
            } catch (Exception e) {
                LOGGER.error("Socket通讯结束关闭时出现异常: ", e);
            }
        }
        return null;
    }


    /**
     * udp协议
     *
     * @param ip       地址
     * @param port     端口
     * @param sendData 发送数据
     * @return 响应数据
     */
    private static String sendMsgWithUdp(String ip, int port, byte[] sendData) {
        DatagramSocket mSocket = null;
        try {
            // 1.初始化DatagramSocket
            mSocket = new DatagramSocket();
            // 2.创建用于发送消息的DatagramPacket
            InetSocketAddress address = new InetSocketAddress(ip, port);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address);
            // 3.向服务端发送消息
            mSocket.send(sendPacket);
            // 4.创建用于接收消息的DatagramPacket
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // 5.接收服务端消息
            mSocket.receive(receivePacket);
            return new String(receiveData);
        } catch (SocketException e) {
            LOGGER.error("连接主机异常: ", e);
        } catch (IOException e) {
            LOGGER.error("读取数据异常: ", e);
        } finally {
            if (null != mSocket) {
                mSocket.close();
            }
        }
        return null;
    }
}
