import lombok.Getter;

import java.util.*;

public class DoubleColorBall {
    @Getter
    private List<Integer> balls = new ArrayList<>();
    private Set<Integer> setBalls = new TreeSet<>();

    public DoubleColorBall() {
        for (int i = 1; i <= 6; i++) {
            setBalls.add((int)(Math.random() * 35 + 1));
        }
        balls.addAll(setBalls);
        balls.add((int)(Math.random() * 15 + 1));
    }
}

class Main {
    public static final int[] intBalls = {
            10,12,30,16,7,17,12
    };

    public static int[] result(List<Integer> balls) {
        int redCount = 0, blueCount = 0;
        for(int i=0;i<balls.size()-1;i++){
            for(int it:intBalls){
                if(balls.get(i)==it){
                    redCount++;
                }
                if(balls.get(balls.size()-1)==it) blueCount++;
            }
        }
        int result[] = {redCount, blueCount};
        return result;
    }

    static void main(String[] args) {
        DoubleColorBall dc = new DoubleColorBall();
        System.out.println(dc.getBalls());
        System.out.println("红球"+result(dc.getBalls())[0]+"个，蓝球"+result(dc.getBalls())[1]+"个");
    }
 }
