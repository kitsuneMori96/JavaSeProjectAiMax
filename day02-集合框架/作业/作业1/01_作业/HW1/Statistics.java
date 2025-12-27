package HW1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
    @Getter
    private static HashMap<String, Integer> votesMap= new HashMap<>();

    static {
        votesMap.put("A", 0);
        votesMap.put("B", 0);
        votesMap.put("C", 0);
        votesMap.put("D", 0);
    }

    public static void setValues(String key, int value) {
        votesMap.put(key, value);
    }
}

@Getter
class StudentVote {
    private final String Attractions;

    public StudentVote(String a) {
        Attractions = a;
    }

}

class Mian {
    private static ArrayList<StudentVote> votes = new ArrayList<>();
    static {
        for(int i=0;i<60;i++){
            int choice = (int)(Math.random()*4);
            switch(choice){
                case 0:
                    votes.add(new StudentVote("A"));
                    break;
                case 1:
                    votes.add(new StudentVote("B"));
                    break;
                case 2:
                    votes.add(new StudentVote("C"));
                    break;
                case 3:
                    votes.add(new StudentVote("D"));
                    break;
                default:
                    break;
            }
        }
    }

    public static void addVote() {
        for(StudentVote v:votes){
            Statistics.setValues(v.getAttractions(), Statistics.getVotesMap().get(v.getAttractions())+1);
        }
    }

    public static void main(String[] args) {
        addVote();
        System.out.println(Statistics.getVotesMap());
    }

}