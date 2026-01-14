import java.net.*;
import java.io.*;

public class SimpleChatClient {
    private String hostname;
    private int port;

    public SimpleChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try {
            // 连接服务器
            Socket socket = new Socket(hostname, port);
            System.out.println("已连接到服务器 " + hostname + ":" + port);

            // 启动一个独立线程来持续接收服务器消息
            new Thread(new MessageReceiver(socket)).start();

            // 在主线程中处理用户输入并发送
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true); // auto-flush

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                serverOut.println(userInputLine);
            }

            // 关闭连接
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("无法找到服务器主机: " + hostname);
        } catch (IOException e) {
            System.err.println("与服务器通信时发生I/O错误: " + e.getMessage());
        }
    }

    // 负责接收服务器消息的线程任务
    static class MessageReceiver implements Runnable {
        private Socket socket;
        public MessageReceiver(Socket socket) { this.socket = socket; }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String responseLine;
                // 持续监听并打印服务器发来的消息
                while ((responseLine = in.readLine()) != null) {
                    System.out.println(responseLine);
                }
            } catch (IOException e) {
                System.out.println("与服务器的连接已断开");
            }
        }
    }

    public static void main(String[] args) {
        // 运行客户端，指定服务器地址和端口
        SimpleChatClient client = new SimpleChatClient("localhost", 12345);
        client.start();
    }
}