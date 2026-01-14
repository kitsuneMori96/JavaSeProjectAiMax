// 引入必要的包
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class SimpleChatServer {
    // 使用线程安全的集合来管理所有已连接的客户端Socket
    private static final ConcurrentLinkedQueue<Socket> clientSockets = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws IOException {
        int port = 12345;
        // 创建服务器Socket，监听指定端口
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已启动，监听端口: " + port);

            while (true) {
                // 接受客户端连接，此方法会阻塞直到有客户端连接
                Socket clientSocket = serverSocket.accept();
                // 将新连接的客户端加入管理集合
                clientSockets.add(clientSocket);
                System.out.println("新客户端连接: " + clientSocket.getRemoteSocketAddress());

                // 为每个客户端创建一个新线程进行处理
                new Thread(new ClientHandler(clientSocket)).start();
            }
        }
    }

    // 处理单个客户端的线程任务
    static class ClientHandler implements Runnable {
        private Socket socket;
        public ClientHandler(Socket socket) { this.socket = socket; }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String inputLine;
                // 持续读取客户端发来的消息
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("收到消息: " + inputLine);
                    // 将收到的消息广播给所有其他客户端
                    broadcastMessage(inputLine, this.socket);
                }
            } catch (IOException e) {
                System.out.println("客户端连接异常断开");
            } finally {
                // 客户端断开后，将其从管理集合中移除
                clientSockets.remove(socket);
                try { socket.close(); } catch (IOException e) {}
                System.out.println("客户端连接已清理");
            }
        }

        // 广播消息给除发送者外的所有客户端
        private void broadcastMessage(String message, Socket senderSocket) {
            for (Socket client : clientSockets) {
                if (client != senderSocket && !client.isClosed()) {
                    try {
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        out.println(message);
                    } catch (IOException e) {
                        // 如果发送失败，认为该客户端已断开，将其移除
                        clientSockets.remove(client);
                    }
                }
            }
        }
    }
}