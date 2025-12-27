package HW1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student implements Comparable<Student> {
    private String name;
    private int socre;

    public void setScore(int score) {
        socre = score;
    }

    @Override
    public int compareTo(Student s) {
        return -(this.socre - s.socre);
    }

    public int getScore() {
        return socre;
    }
}
