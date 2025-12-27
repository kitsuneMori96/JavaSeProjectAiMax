package HW2;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class User{
    private String name;
    public String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class UserManager{
    private static HashSet<User> userSet = new HashSet<User>();

    static {
        userSet.add(new User("Alice", "123456"));
        userSet.add(new User("Bob", "123456"));
        userSet.add(new User("Charlie", "123456"));
    }

    public HashSet<User> getUserSet() {
        return userSet;
    }

    static void main(String[] args) {
        UserManager userManager = new UserManager();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入用户名：");
            String name = scanner.nextLine();
            System.out.print("请输入密码：");
            String password = scanner.nextLine();
            User user = new User(name, password);
            if(userManager.getUserSet().add(user)){
                System.out.println("注册成功！");
            }
            else{
                System.out.println("用户名已存在！");
            }
        }
    }
}