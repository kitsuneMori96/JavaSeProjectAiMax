package demo3;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

class Rewrite implements Callable<Integer> {

    private int sum;

    @Override
    public Integer call() throws Exception {
        while (Word.getCount().get() > 0){
            if(Word.copywriting()) sum++;
            try {
                Thread.sleep(1);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}

@Data
public class Word {

    @Getter
    private static AtomicInteger count = new AtomicInteger();
    private static String word;

    public Word(int count, String word) {
        this.count.set(count);
        this.word = word;
    }

    public static boolean copywriting() {
        synchronized (Word.class) {
            if (count.get() > 0) {
                count.set(count.get() - 1);
                System.out.println(currentThread().getName()+"线程"+"抄写一遍"+word+"..."+"两人总共还要抄写"+count+"遍");
                return true;
            }
            return false;
        }
    }

}

class Test {
    public static void main(String[] args) throws Exception {
        Word word = new Word(100, "helloWorld");
        FutureTask<Integer> task1= new FutureTask<> (new Rewrite());
        FutureTask<Integer> task2 = new FutureTask<> (new Rewrite());
        Thread t1 = new Thread(task1, "小王");
        Thread t2 = new Thread(task2, "小明");
        t1.start();
        t2.start();
        System.out.println(t1.getName()+"抄写了"+task1.get()+"遍");
        System.out.println(t2.getName()+"抄写了"+task2.get()+"遍");
    }
}