package HW1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Data
@AllArgsConstructor

public class User {
    @Getter
    private static HashMap<String, String> userMap = new HashMap<>();

    public static void addUser(String username, String password) {
        userMap.put(username, password);
    }

}

class Main {
        static void main(String[] args) {
        User.addUser("user1", "password1");
        User.addUser("user2", "password2");
        User.addUser("user3", "password3");
        HashMap<String, String> userMap = User.getUserMap();
        userMap.forEach((username, password) -> {
            System.out.println("用户名：" + username + " 密码：" + password);
        });
        register();
    }

    public static void showUser() {

    }

    public static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        for(Map.Entry<String, String> entry : User.getUserMap().entrySet()){
            if(entry.getKey().equals(username)){
                System.out.println("用户名已存在！");
                register();
                return;
            }
        }
        User.addUser(username, password);
        System.out.println("注册成功！");
    }
}