package demo4;

import lombok.Getter;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

public class Sweepstakes {
    @Getter
    private static ArrayList <String> prizes;
    @Getter
    private static final Lock lock = new ReentrantLock();

    static {
        prizes = new ArrayList<>();
        prizes.add("iPhone 12 Pro Max");
        prizes.add("Samsung Galaxy S21 Ultra");
        prizes.add("Nokia 1100");
        prizes.add("Sony Xperia 1");
        prizes.add("Xiaomi Redmi Note 10 Pro");
        prizes.add("Huawei P40 Pro");
        prizes.add("OnePlus 8 Pro");
        prizes.add("Oppo Reno 5");
        prizes.add("Vivo V20 Pro");
        prizes.add("Sony Xperia 5");
    }


}

class SweepstakesManager implements Runnable {
    Lock lock= Sweepstakes.getLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try{
                if(Sweepstakes.getPrizes().isEmpty()) break;
                int index = (int)(Math.random() * Sweepstakes.getPrizes().size());
                System.out.println(currentThread().getName() + "组抽出了一个"+Sweepstakes.getPrizes().get(index));
                Sweepstakes.getPrizes().remove(index);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                lock.unlock();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Main {
    static void main(String[] args) {
        Runnable manager1 = new SweepstakesManager();
        Runnable manager2 = new SweepstakesManager();
        Thread t1 = new Thread(manager1, "Manager1");
        Thread t2 = new Thread(manager2, "Manager2");
        t1.start();
        t2.start();
    }
}