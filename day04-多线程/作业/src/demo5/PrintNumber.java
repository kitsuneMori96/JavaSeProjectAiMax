package demo5;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumber implements Runnable {

    @Override
    public void run() {
        while (true) {
            Main.getLock().lock();
            try {
                if(Main.getList().isEmpty()) break;
                System.out.print(Main.getList().remove(0)+" ");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                Main.getLock().unlock();
            }
        }
    }
}

class printChar implements Runnable {

    @Override
    public void run() {
        while (true) {
            Main.getLock().lock();
            try {
                if(Main.getCharList().isEmpty()) break;
                System.out.print(Main.getCharList().removeFirst()+" ");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                Main.getLock().unlock();
            }
        }
    }
}

class Main {
    @Getter
    private static final Lock lock = new ReentrantLock();
    @Getter
    private static List<Integer> list = new ArrayList<>();
    @Getter
    private static List<Character> charList = new ArrayList<>();

    static {
        for(int i=1;i<=52;i++) {
            list.add(i);
        }
        for(char c='A';c<='Z';c++) {
            charList.add(c);
        }
    }

    static void main() {
        new Thread(new PrintNumber()).start();
        new Thread(new printChar()).start();
        new Thread(new printChar()).start();
        new Thread(new PrintNumber()).start();
    }
}
