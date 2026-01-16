import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, String> userMap = new HashMap<>();
    static void main(String[] args) {
        readeUserList();
        System.out.println(userMap);
        start();
    }

    public static void readeUserList() {
        try (
                InputStream inputStream = new FileInputStream("Java基础加强考试试卷/题目/src/userList.txt");
                DataInputStream dis = new DataInputStream(inputStream);
            ) {
            while (dis.available() != 0) {
                userMap.put(dis.readUTF(), dis.readUTF());
            }
        }
        catch (Exception e) {
            System.out.println("读取用户列表失败！");
        }
    }

    public static void start() {
        try(
                ServerSocket ss = new ServerSocket(8080);
        ) {
            while (true) {
                System.out.println("服务器正在等待客户端连接...");
                Socket socket = ss.accept();
                new Thread(() -> {
                    try (
                            InputStream inputStream = socket.getInputStream();
                            DataInputStream dataInputStream = new DataInputStream(inputStream);
                            OutputStream os = socket.getOutputStream();
                            DataOutputStream dos = new DataOutputStream(os);
                            DataOutputStream dos2 = new DataOutputStream(new FileOutputStream("Java基础加强考试试卷/题目/src/userList.txt",true ));
                    ) {
                        System.out.println("服务器 accept 了一个连接");
                        int type = dataInputStream.readInt();
                        String username = dataInputStream.readUTF();
                        String password = dataInputStream.readUTF();
                        if (type == 1) {
                            boolean isUsedUsername = userMap.containsKey(username);
                            if(isUsedUsername){
                                dos.writeInt(1);
                                dos.writeUTF("用户名已被使用！");
                            }
                            else {
                                userMap.put(username, password);
                                dos.writeInt(2);
                                dos.writeUTF("注册成功！");
                                dos2.writeUTF(username);
                                dos2.writeUTF(password);
                                dos2.flush();
                            }
                        }
                        else if (type == 2) {
                            if (userMap.containsKey(username)) {
                                if (userMap.get(username).equals(password)) {
                                    dos.writeInt(3);
                                    dos.writeUTF("登录成功！");
                                }
                                else {
                                    dos.writeInt(4);
                                    dos.writeUTF("密码错误！");
                                }
                            }
                            else {
                                dos.writeInt(4);
                                dos.writeUTF("用户名不存在！");
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

        } catch (IOException e) {
            System.out.println("服务器出现问题，请检查！");
        }
    }
}
