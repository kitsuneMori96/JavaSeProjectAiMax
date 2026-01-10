package com.itheima.ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientReaderThread extends Thread{
    private Socket socket;
    private DataInputStream dis;
    private ClientChatFrame win;
    public ClientReaderThread( Socket socket, ClientChatFrame win) {
        this.win = win;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 接收的消息可能有很多种类型：1、在线人数更新的数据 2、群聊消息
            dis = new DataInputStream(socket.getInputStream());
            while (true) {
                int type = dis.readInt(); // 1、2、3
                switch (type){
                    case 1:
                        // 服务端发来的在线人数更新消息
                        updateClientOnLineUserList();
                        break;
                    case 2:
                        // 服务端发送来的群聊消息
                        getMsgToWin();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMsgToWin() throws Exception {
        // 获取群聊消息
        String msg = dis.readUTF();
        win.setMsgToWin(msg);
    }

    // 更新客户端的在线用户列表
    private void updateClientOnLineUserList() throws Exception {
        // 1、读取有多少个在线用户
        int count = dis.readInt();

        // 2、循环控制读取多少个用户信息。
        String[] names = new String[count];
        for (int i = 0; i < count; i++) {
            // 3、读取每个用户的信息
            String nickname = dis.readUTF();
            // 4、将每个用户的信息添加到数组
            names[i] = nickname;
        }

        // 5、将集合中的数据展示到窗口上
        win.updateOnlineUsers(names);
    }


}
