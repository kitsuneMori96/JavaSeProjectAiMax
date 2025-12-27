package HW1;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student implements Comparable<Student>{
     private String name;
     private double score;

    @Override
    public int compareTo(Student o) {
        return Double.compare(this.score, o.score);
    }
}

class Operator {
    private static ArrayList<Student> students;

    public Operator() {
        students = new ArrayList<>();
    }

    public static void main(String[] args) {
        Operator operator = new Operator();
        operator.addStudent(new Student("Alice", 85));
        operator.addStudent(new Student("Bob", 92));
        operator.addStudent(new Student("Charlie", 78));
        operator.addStudent(new Student("David", 90));
        Collections.sort(operator.students);
        students.forEach(System.out::println);
    }

    private void addStudent(Student student) {
        students.add(student);
    }
}