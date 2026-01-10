package demo1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Bank3 {
    @Setter
    @Getter
    public static int balance = 1000;

}

@AllArgsConstructor
class DrawMoney3 implements Callable<Integer> {
    private String name;

    @Override
    public Integer call() throws Exception {
        for(int i = 0; i < 10; i++){
            Bank3.setBalance(Bank3.getBalance() + 100);
            System.out.println(name + " draw 100, balance is " + Bank3.getBalance());
        }
        return Bank3.getBalance();
    }
}

class Main3 {
    public static void main(String[] args) throws Exception {
        new Bank3();
        FutureTask futureTask = new FutureTask(new DrawMoney3("Alice"));
        FutureTask futureTask2 = new FutureTask(new DrawMoney3("Bob"));
        new Thread(futureTask).start();
        new Thread(futureTask2).start();
        System.out.println("Alice balance is " + futureTask.get());
        System.out.println("Bob balance is " + futureTask2.get());
    }
}