package demo5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

@Data
public class AnnualMeetingEntrance {
    @Getter
    private static final Lock lock = new ReentrantLock();
    @Getter
    private static Set<List<Integer>> set = new HashSet<>();
    @Getter
    @Setter
    private static List<Employee> employees = new ArrayList<>();

    static{
        for (int i = 0; i < 100; i++) {
            do {
                set.add(Employee.LuckyNumbers());
            }
            while (set.size() < 100);
        }

        for(int i=0;i<100;i++){
            Employee employee = new Employee(i);
            employees.add(employee);
        }
    }

}

@AllArgsConstructor
class Employee {
    @Getter
    private int id;
    @Getter
    @Setter
    private List<Integer> LuckyNumbers = new ArrayList<>();



    public Employee(int i) {
        this.id = i;
    }

    public void addLuckyNumber() {
        LuckyNumbers=AnnualMeetingEntrance.getSet().iterator().next();
        AnnualMeetingEntrance.getSet().remove(LuckyNumbers);
    }


    public static List<Integer> LuckyNumbers() {
        Set<Integer> luckyNumbers = new TreeSet<>();
        for (int i = 0; i < 6; i++) {
            luckyNumbers.add((int) (Math.random() * 33 + 1));
        }
        List<Integer> luckyNumbersList = new ArrayList<>(luckyNumbers);
        luckyNumbersList.addAll(luckyNumbers);
        luckyNumbersList.add((int) (Math.random() * 16 + 1));
        return luckyNumbersList;

    }
}

class AnnualMeeting implements Runnable {
    private static Lock lock = AnnualMeetingEntrance.getLock();

    @Override
    public void run() {
        while (!AnnualMeetingEntrance.getEmployees().isEmpty()) {
            lock.lock();
            try {
                Iterator<Employee> iterator = AnnualMeetingEntrance.getEmployees().iterator();
                Employee employee = iterator.next();
                employee.addLuckyNumber();
                System.out.println("编号为:"+employee.getId()+"从"+currentThread().getName()+"入场!"+"拿到的双色球号码是"+employee.getLuckyNumbers());
                AnnualMeetingEntrance.getEmployees().remove(employee);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                lock.unlock();
            }
        }
    }
}

class test {
    static void main(String[] args) {
        AnnualMeeting front = new AnnualMeeting();
        AnnualMeeting back = new AnnualMeeting();
        Thread t1 = new Thread(front,"前门");
        Thread t2 = new Thread(back,"后门");
        t1.start();
        t2.start();
    }
}