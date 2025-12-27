package HW1;

import java.util.*;

public class Lottery {
    HashSet<Integer> red;
    HashSet<Integer> blue;

    public Lottery() {
        red = new HashSet<Integer>();
        blue = new HashSet<Integer>();
    }
}

class LotteryTest {


    public static void main(String[] args) {
        Lottery lottery = new Lottery();
        for(int i = 0; i < 6; i++){
            lottery.red.add((int) (Math.random() * 33 + 1));
        }
        lottery.blue.add((int) (Math.random() * 16 + 1));
        List<Integer> red = new ArrayList<>(lottery.red);
        List<Integer> blue = new ArrayList<>(lottery.blue);
        Collections.sort(red);
        Collections.sort(blue);
        System.out.println(red + " " + blue);
    }
}
