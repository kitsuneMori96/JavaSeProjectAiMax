package User;

import lombok.Getter;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 用户类，程序入口
public class User {
    static void main(String[] args) {
        new LoginFrame();
    }
}

// 登录窗口类，负责用户登录
class LoginFrame {
    public LoginFrame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("这是登录界面 请输入用户名:");
        String username = scanner.nextLine();
        new ChatFrame(username);
    }
}

// 聊天窗口类，负责聊天功能
class ChatFrame {
    public static final int PORT = 8080;
    private static String username;
    private static List<String> userList = new ArrayList<>();
    @Getter
    private static Socket socket;

    public ChatFrame(String username) {
        ChatFrame.username = username;
        System.out.println("欢迎" + username + "登录聊天室");
        try(
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket("127.0.0.1", PORT)
        ) {
            ChatFrame.socket = socket;
            new Thread(new ReceiveThread()).start();
            send(1, socket, scanner);
            while (true) {
                send(2, socket, scanner);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 发送消息方法，根据类型发送不同消息
    public void send(int type, Socket socket, Scanner scanner) {
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        if(type==1) {
            //登录
            //发送登录消息
            try {
                dataOutputStream.writeInt(1);
                dataOutputStream.writeUTF(username);
                dataOutputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            String content = scanner.nextLine();
            //发送消息
            try {
                dataOutputStream.writeInt(2);
                dataOutputStream.writeUTF(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// 接收消息线程类，负责接收服务器发送的消息
class ReceiveThread implements Runnable {
    private List<String> userList = new ArrayList<>();

    @Override
    public void run() {
        while (true) {
            Socket socket = ChatFrame.getSocket();
            try {
                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                int type = dataInputStream.readInt();
                if(type == 1){
                    int len = dataInputStream.readInt();
                    userList.clear();
                    for (int i = 0; i < len; i++) {
                        String user = dataInputStream.readUTF();
                        userList.add(user);
                    }
                    System.out.println("当前在线用户:"+userList);
                }
                else if(type == 2){
                    String content = dataInputStream.readUTF();
                    System.out.println(content);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
