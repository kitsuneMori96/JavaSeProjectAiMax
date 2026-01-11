package demo4;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DoubleColorBallUtils {
    @Getter
    private static boolean over = true;

    public static final Lock lock = new ReentrantLock();

    public static List<Integer> LuckyNumbers() {
        Set<Integer> luckyNumbers = new TreeSet<>();
        while (luckyNumbers.size() < 6) {
            luckyNumbers.add((int) (Math.random() * 33 + 1));
        }
        List<Integer> luckyNumbersList = new ArrayList<>(luckyNumbers);
        luckyNumbersList.add((int) (Math.random() * 16 + 1));
        return luckyNumbersList;
    }

    public static boolean getOver() {
        return over;
    }

    public static void setOver(boolean over) {
        DoubleColorBallUtils.over = over;
    }

    public static void print(){
        if (over) {
            System.out.println("前门入场的员工数量"+Manager.getFrontCounter());
            System.out.println("后门入场的员工数量"+Manager.getBackCounter());
        }
        setOver(false);
    }
}

class Employee {
    @Getter
    private int id;
    @Setter
    @Getter
    private List<Integer> luckyNumbers;

     public Employee(int id) {
        this.id = id;
    }
}

@Data
class Manager {
    @Getter
    private static int frontCounter = 0;
    @Getter
    private static int backCounter = 0;
    @Getter
    private static Set<List<Integer>> luckyNumbersSet = new HashSet<>();

    public static void addLuckyNumber(int number) {
        while (luckyNumbersSet.size() <number){
            luckyNumbersSet.add(DoubleColorBallUtils.LuckyNumbers());
        }
    }

    public static void addCounter(String name) {
        if(name.equals("前门")) {
            frontCounter++;
        }
        else if(name.equals("后门")) {
            backCounter++;
        }
    }
    @Getter
    private static List<Employee> employees = new ArrayList<>();
    static {
        for(int i = 1; i <= 100; i++){
            employees.add(new Employee(i));
        }
    }
}

class Entrance implements Runnable {
    @Override
    public void run() {
        while (true) {
            DoubleColorBallUtils.lock.lock();
            try{
                if(Manager.getEmployees().isEmpty()) {
                    DoubleColorBallUtils.print();
                    break;
                }
                Employee employee = Manager.getEmployees().remove(0);
                employee.setLuckyNumbers(Manager.getLuckyNumbersSet().iterator().next());
                Manager.getLuckyNumbersSet().remove(employee.getLuckyNumbers());
                System.out.println( Thread.currentThread().getName() +"入场" + employee.getId() + "号员工的幸运号码为：" + employee.getLuckyNumbers());
                Manager.addCounter(Thread.currentThread().getName());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                DoubleColorBallUtils.lock.unlock();
            }
        }
    }
}

class test {
    static void main(String[] args) {
        Manager manager = new Manager();
        manager.addLuckyNumber(100);
        Thread t1 = new Thread(new Entrance(), "前门");
        Thread t2 = new Thread(new Entrance(), "后门");
        t1.start();
        t2.start();

    }
}