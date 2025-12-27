package HW3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private String name;
    private String password;
}

class Operator{
    private ArrayList<User> userList = new ArrayList<>();

    public Operator() {
        userList = new ArrayList<>();
        userList.add(new User("Alice", "123456"));
        userList.add(new User("Bob", "654321"));
        userList.add(new User("Charlie", "qwerty"));
    }

    public void addUser() {
        System.out.println("请输入用户名：");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getName().equals(name)) {
                System.out.println("用户名已存在！");
                addUser();
                return;
            }
        }
        System.out.println("请输入密码：");
        String password = sc.next();
        userList.add(new User(name, password));
        System.out.println("添加成功！");
        showUser();
    }

    private void showUser() {
        System.out.println("用户列表：");
        userList.forEach(System.out::println);
    }

    static void main(String[] args) {
        Operator operator = new Operator();
        operator.addUser();
    }
}