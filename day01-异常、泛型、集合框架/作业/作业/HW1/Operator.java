package HW1;

import java.util.*;

public class Operator {
    private static ArrayList<Student> students;

    public Operator() {
        students = new ArrayList<>();
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    static void main(String[] args) {
        Operator operator = new Operator();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add student");
            System.out.println("2. 退出程序开始计算");
            System.out.print("请输入选项：");
            String option = sc.next();
            try{
                int i = Integer.parseInt(option);
                switch (i) {
                    case 1: addStudent(); break;
                    case 2: operator.calculate(); break;
                    default: System.out.println("输入有误，请重新输入！");
                }
            }
            catch(RuntimeException e){
                System.out.println("输入有误，请重新输入！");
            }

        }
    }

    private void calculate() {
        Collections.sort(students);
        System.out.println("录取学生列表：");
        int count = 0;
        for (Student student : students) {
            if(count++<3) System.out.println(student.getName() + " " + student.getScore());
        }
    }

    private static void addStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入学生姓名：");
        String name = sc.next();
        System.out.print("请输入学生分数：");
        int score = sc.nextInt();
        Student student = new Student();
        student.setName(name);
        student.setScore(score);
        addStudent(student);
    }
}
