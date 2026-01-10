package demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Bank2 {
    @Getter
    @Setter
    private static int balance=1000;

}

@AllArgsConstructor
class bankThread extends Thread{
    private String name;

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            Bank2.setBalance(Bank2.getBalance()+100);
            System.out.println(name+" 存款一百元后余额为："+Bank2.getBalance());
        }
    }
}

class main{
    static void main(String[] args) {
        new bankThread("张三").start();
        new bankThread("李四").start();
    }
}