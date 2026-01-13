package Server;

import lombok.Getter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final int PORT = 8080;
    @Getter
    private static Map<Socket, String> userSocketMap = new HashMap<>();

    static void main(String[] args) throws IOException {
        // TODO 启动服务器
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("服务器启动成功！");
        while (true) {
            // TODO 等待客户端连接
            if(userSocketMap.isEmpty()) System.out.println("等待客户端连接...");
            // TODO 创建线程处理客户端请求
            Socket socket = server.accept();
            new ServerThread(socket);
        }
    }

    public static void sendMsg(String msg, Socket socket,int type) throws IOException {
        // TODO 发送消息
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(type);
        dataOutputStream.writeUTF(msg);
        dataOutputStream.flush();
    }
}

class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO 处理客户端请求
        try {
            while (true) {
                InputStream inputStream = socket.getInputStream();
                //打印流
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                //读取输入流
                if (!Server.getUserSocketMap().containsKey(socket)){
                    System.out.println("客户端连接成功！");
                    Server.getUserSocketMap().put(socket, reader.readLine());
                }
                else {

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
