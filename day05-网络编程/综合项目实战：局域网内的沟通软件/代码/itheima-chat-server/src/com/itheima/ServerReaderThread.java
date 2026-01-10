package com.itheima;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class ServerReaderThread extends Thread{
    private Socket socket;
    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 接收的消息可能有很多种类型：1、登录消息（包含昵称） 2、群聊消息 3、私聊消息
            // 所以客户端必须声明协议发送消息
            // 比如客户端先发1，代表接下来是登录消息。
            // 比如客户端先发2，代表接下来是群聊消息。
            // 先从socket管道中接收客户端发送来的消息类型编号
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                int type = dis.readInt(); // 1、2、3
                switch (type){
                    case 1:
                        // 客户端发来了登录消息，接下来要接收昵称数据，再更新全部在线客户端的在线人数列表。
                        String nickname = dis.readUTF();
                        // 把这个登录成功的客户端socket存入到在线集合。
                        Server.onLineSockets.put(socket, nickname);
                        // 更新全部客户端的在线人数列表
                        updateClientOnLineUserList();
                        break;
                    case 2:
                        // 客户端发来了群聊消息，接下来要接收群聊消息内容，再把群聊消息转发给全部在线客户端。
                        String msg = dis.readUTF();
                        sendMsgToAll(msg);
                        break;
                    case 3:
                        // 客户端发来了私聊消息，接下来要接收私聊消息内容，再把私聊消息转发给指定客户端。
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("客户端下线了："+ socket.getInetAddress().getHostAddress());
            Server.onLineSockets.remove(socket); // 把下线的客户端socket从在线集合中移除
            updateClientOnLineUserList(); // 下线了用户也需要更新全部客户端的在线人数列表。
        }
    }

    // 给全部在线socket推送当前客户端发来的消息
    private void sendMsgToAll(String msg) {
        // 一定要拼装好这个消息，再发给全部在线socket.
        StringBuilder sb = new StringBuilder();
        String name = Server.onLineSockets.get(socket);

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EEE a");
        String nowStr = dtf.format(now);

        String msgResult = sb.append(name).append(" ").append(nowStr).append("\r\n")
                .append(msg).append("\r\n").toString();
        // 推送给全部客户端socket
        for (Socket socket : Server.onLineSockets.keySet()) {
            try {
                // 3、把集合中的所有用户名称，通过socket管道发送给客户端
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(2); // 1代表告诉客户端接下来是在线人数列表信息  2 代表发的是群聊消息
                dos.writeUTF(msgResult);
                dos.flush(); // 刷新数据！
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateClientOnLineUserList() {
        // 更新全部客户端的在线人数列表
        // 拿到全部在线客户端的用户名称，把这些名称转发给全部在线socket管道。
        // 1、拿到当前全部在线用户昵称
        Collection<String> onLineUsers = Server.onLineSockets.values();
        // 2、把这个集合中的所有用户都推送给全部客户端socket管道。
        for (Socket socket : Server.onLineSockets.keySet()) {
            try {
                // 3、把集合中的所有用户名称，通过socket管道发送给客户端
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(1); // 1代表告诉客户端接下来是在线人数列表信息  2 代表发的是群聊消息
                dos.writeInt(onLineUsers.size()); // 告诉客户端，接下来要发多少个用户名称
                for (String onLineUser : onLineUsers) {
                    dos.writeUTF(onLineUser);
                }
                dos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
