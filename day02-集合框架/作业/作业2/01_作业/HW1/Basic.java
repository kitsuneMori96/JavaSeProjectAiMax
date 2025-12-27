package HW1;

import lombok.Getter;

import java.util.HashMap;

public class Basic {
    @Getter
    private static HashMap<String,HashMap<Integer, String>> basicMap = new HashMap<>();
    static {
        basicMap.put("java基础班", Employee.getEmployeeMap(1) );
        basicMap.put("java高级班", Employee.getEmployeeMap(2) );
    }

}

class Employee {
    private static HashMap<Integer, String> employeeMap1 = new HashMap<>();
    private static HashMap<Integer, String> employeeMap2 = new HashMap<>();

    static {
        employeeMap1.put(1, "John");
        employeeMap1.put(2, "Mary");
        employeeMap2.put(1, "Tom");
        employeeMap2.put(2, "Jerry");
    }

    public static HashMap<Integer, String> getEmployeeMap(int id) {
        if (id == 1) {
            return employeeMap1;
        } else if (id == 2) {
            return employeeMap2;
        } else {
            return null;
        }
    }
}

class Test {
    public static void main(String[] args) {
        for(String key : Basic.getBasicMap().keySet()){
            System.out.println(key);
            System.out.println("-------------------------");
            for(Integer id : Basic.getBasicMap().get(key).keySet()){
                System.out.println(id+" : "+Basic.getBasicMap().get(key).get(id));
            }
        }
    }
}