import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static void main() {
        try (
                Scanner scanner = new Scanner(System.in);
                ) {
            System.out.println("请选择功能：1.注册 2.登录 3.退出");
            int type = scanner.nextInt();
            if (type == 1) {
                Client.register();
            }
            else if (type == 2) {
                Client.login();
            }
            else if (type == 3) {
                System.exit(0);
            }
            else {
                System.out.println("输入错误，请重新输入！");
            }
        }
    }

    public static void communication (int types) {
        int type = types;
        try(
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket("localhost", 8080);
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                InputStream is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
        ) {
            System.out.println("输入用户名：");
            String username = scanner.nextLine();
            System.out.println("输入密码：");
            String password = scanner.nextLine();
            if(username.equals("exit") || password.equals("exit")) {
                System.exit(0);
            }
            dos.writeInt(type);
            dos.writeUTF(username);
            dos.writeUTF(password);
            dos.flush();
            int result = dis.readInt();
            String s = dis.readUTF();
            System.out.println(s);
            if (result == 1) {
                register();
            }
            else if (result == 2) {
                login();
            }
            else if (result == 4) {
                login();
            }
        }
        catch (Exception e) {
            System.out.println("出现问题，请重新输入！");
        }
    }

    public static void register () {
        communication(1);
    }

    public static void login () {
        communication(2);
    }
}
