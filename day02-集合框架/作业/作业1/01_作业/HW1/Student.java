package HW1;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

