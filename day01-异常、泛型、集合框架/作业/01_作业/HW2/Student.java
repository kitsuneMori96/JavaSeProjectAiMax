package HW2;

import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@Data

public class Student {
    @Getter
    private String name;
    private String address;

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }

}

class Operator {

    private ArrayList<Student> students;

    public Operator() {
        students = new ArrayList<Student>();
        students.add(new Student("Alice", "Beijing"));
        students.add(new Student("Bob", "Shanghai"));
        students.add(new Student("Charlie", "Guangzhou"));
        students.add(new Student("David", "Shenzhen"));
    }

    public void printAllStudents() {
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getAddress());
        }
    }

    public void modifyStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("要修改的学生名字");
        String name = sc.next();
        Iterator<Student> iteable = students.iterator();
        while(iteable.hasNext()) {
            Student student = iteable.next();
            if (student.getName().equals(name)) {
                System.out.println("现在的地址是" + student.getAddress());
                System.out.println("请输入新的地址");
                String newAddress = sc.next();
                student.setAddress(newAddress);
                System.out.println("修改成功");
                printAllStudents();
                break;
            }
        }
    }

    static void main(String[] args) {
        Operator operator = new Operator();
        operator.modifyStudent();
    }
}