package demo3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RedEnvelopeRain {
    public static final Lock lock = new ReentrantLock();
    @Getter
    @Setter
    private static boolean isOver = false;
    private static int totalNum;
    private static int minMinMoney;
    private static int minMaxMoney;
    private static int maxMinMoney;
    private static int maxMaxMoney;
    @Getter
    private static List<Integer> redEnvelopeList = new ArrayList<>();

    public static void over(){
        if(!isOver){
            isOver = true;
            System.out.println("RedEnvelopeRain over!");
            EmployeeManager.employeeSort();
            for(Employee employee : EmployeeManager.getEmployeeList()){
                System.out.println("Employee " + employee.getId() + " has " + employee.getMoney() + " money.");
            }
        }
    }

    public RedEnvelopeRain(int totalNum, int minMinMoney, int minMaxMoney, int maxMinMoney, int maxMaxMoney, double percentage) {
        RedEnvelopeRain.totalNum = totalNum;
        RedEnvelopeRain.minMinMoney = minMinMoney;
        RedEnvelopeRain.minMaxMoney = minMaxMoney;
        RedEnvelopeRain.maxMinMoney = maxMinMoney;
        RedEnvelopeRain.maxMaxMoney = maxMaxMoney;

        for(int i=0;i<(totalNum*(1-percentage));i++){
            //写出代码，添加金额在minMinMoney到minMaxMoney之间的红包到redEnvelopeList中
            redEnvelopeList.add((int)(Math.random()*(minMaxMoney-minMinMoney))+1+minMinMoney);
        }
        for(int i=0;i<(totalNum*percentage);i++){
            redEnvelopeList.add((int)(Math.random()*(maxMaxMoney-maxMinMoney))+1+maxMinMoney);
        }
    }

}

class Employee {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private int money;

    public Employee(int id){
        this.id = id;
    }
}

class EmployeeManager {
    @Getter
    private static int employeeNum;
    @Getter
    private static List<Employee> employeeList = new ArrayList<>();

    public EmployeeManager(int employeeNum){
        EmployeeManager.employeeNum = employeeNum;
        for(int i=1;i<=employeeNum;i++){
            employeeList.add(new Employee(i));
        }
    }

    public static void employeeSort(){
        employeeList.sort((o1, o2) -> o2.getMoney()-o1.getMoney());
    }

}

class Grab implements Runnable{
    private final Employee employee;

    public Grab(Employee employee){
        this.employee = employee;
    }

    @Override
    public void run() {
        //写出代码，随机从redEnvelopeList中取出一个红包，并给employee.money加上这个红包的金额
        while (true) {
            RedEnvelopeRain.lock.lock();
            try{
                if(!RedEnvelopeRain.getRedEnvelopeList().isEmpty()){
                    int money = RedEnvelopeRain.getRedEnvelopeList().remove((int)(Math.random()*RedEnvelopeRain.getRedEnvelopeList().size()));
                    employee.setMoney(employee.getMoney()+money);
                    System.out.println("Employee " + employee.getId() + " grab a red envelope with " + money + " money.");
                }
                else {
                    RedEnvelopeRain.over();
                    break;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally{
                RedEnvelopeRain.lock.unlock();
            }
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class main {
    static void main(String[] args) {
        new RedEnvelopeRain(200, 1, 30, 31, 100, 0.2);
        new EmployeeManager(100);
        for(int i = 0; i< EmployeeManager.getEmployeeNum(); i++){
            new Thread(new Grab(EmployeeManager.getEmployeeList().get(i))).start();
        }
    }
}