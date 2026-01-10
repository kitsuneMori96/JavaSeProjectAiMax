package demo1;

import lombok.*;

@Data

public class Bank1 {
    @Getter
    @Setter
    private static int balance = 1000;
}

class DrawMoney implements Runnable{
    @Getter
    @Setter

    private String name ;

    public DrawMoney(String s) {
        name = s;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            Bank1.setBalance(Bank1.getBalance() + 100);
            System.out.println(name + " draw 100, balance is " + Bank1.getBalance());
        }
    }
}

class TestBank {
    public static void main(String[] args) {
        Bank1 bank = new Bank1();
        new Thread(new DrawMoney("小王")).start();
        new Thread(new DrawMoney("小李")).start();
    }
}