package Server;

import lombok.Getter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Server.Server.PORT;
import static Server.localServer.flushUserList;

// 本地服务器类，负责管理用户列表和用户连接
public class localServer {
    @Getter
    private static List<String> userList = new ArrayList<>();
    @Getter
    private static Map<Socket,String> userMap = new HashMap<>();

    // 主方法，启动服务器
    static void main(String[] args) {
        startServer();
    }

    // 刷新用户列表，移除断开连接的用户
    public static void flushUserList( Socket accept ) {
        userList.remove(userMap.get(accept));
        userMap.remove(accept);
        new Thread(new userListThread(accept)).start();
    }

    // 启动服务器，监听客户端连接
    public static void startServer() {
        Socket accept = null;
        try (
                ServerSocket serverSocket = new ServerSocket(PORT)
        ) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                if(userList.isEmpty()) System.out.println("Waiting for client...");
                accept = serverSocket.accept();
                new Thread(new startServerThread(accept)).start();
                System.out.println("Client connected."+ userList.size() + " users online."+ userList);
            }
        } catch (IOException e) {
            flushUserList(accept);
            throw new RuntimeException(e);
        }
    }
}

// 处理客户端连接的线程类
class startServerThread implements Runnable {
    private Socket accept;
    private boolean flag = true;

    public startServerThread(Socket accept) {
        this.accept = accept;
    }
    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = accept.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            if(flag){
                flag = false;
                dataInputStream.readInt();
                String s = dataInputStream.readUTF();
                localServer.getUserList().add(s);
                localServer.getUserMap().put(accept, s);
                new Thread(new userListThread(accept)).start();
            }
            new Thread(new ServerRunnable(accept)).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

// 处理服务器端消息传输的线程类
class ServerRunnable implements  Runnable{
    private Socket socket;

    public ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true) {
                if(dataInputStream.readInt()==2) {
                    String sms = dataInputStream.readUTF();
                    String sender = localServer.getUserMap().get(socket)+":"+sms;
                    for(Socket client:localServer.getUserMap().keySet()){
                        OutputStream outputStream = client.getOutputStream();
                        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        dataOutputStream.writeInt(2);
                        dataOutputStream.writeUTF(sender);
                    }
                }
            }


        } catch (IOException e) {
            flushUserList(socket);
            throw new RuntimeException(e);
        }
    }
}

// 更新客户端用户列表的线程类
class userListThread implements Runnable {
    private Socket accept;

    public userListThread(Socket accept) {
        this.accept = accept;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        try {
            for(Socket client:localServer.getUserMap().keySet()){
                outputStream = client.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeInt(1);
                dataOutputStream.writeInt(localServer.getUserList().size());
                for (int i = 0; i < localServer.getUserList().size(); i++) {
                    dataOutputStream.writeUTF(localServer.getUserList().get(i));
                }
            }
        } catch (IOException e) {
            flushUserList(accept);
            throw new RuntimeException(e);
        }

    }
}
