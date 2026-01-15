import lombok.Getter;

import java.util.*;

public class Prisoner {
    @Getter
    private int id;
    @Getter
    private List<Integer> location =  new ArrayList<>();

    public Prisoner(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Prisoner{" +
                "id=" + id +
                ", location=" + location +
                '}';
    }
}

class PrisonerManager {
    private static List<Prisoner> prisoners = new ArrayList<>();

    public static void initializePrisoners() {
        Set<Integer> ids = new HashSet<>();
        while (ids.size() < 100) {
            ids.add((int) (Math.random() * 200+1));
        }
        for(int id : ids){
            prisoners.add(new Prisoner(id));
        }
        System.out.println("The initial prisoner list is " + prisoners);
        System.out.println("-------------------------------------------");
        id();
    }

    public static void execution () {
        while (prisoners.size() > 1) {
            int count = 1;
            Iterator<Prisoner> it = prisoners.iterator();
            while (it.hasNext()) {
                it.next();
                if(count % 2 == 1) it.remove();
                count++;
            }
            id();
            System.out.println("The updated prisoner list is " + prisoners);
            System.out.println("-------------------------------------------");
        }
        System.out.println("The last prisoner is " + prisoners.getFirst().getId());
        System.out.println("The last prisoner's location is " + prisoners.getFirst().getLocation().getFirst());
    }

    public static void id () {
        int count = 1;
        for(Prisoner it: prisoners){
            it.getLocation().add(count);
            count++;
        }
    }
}

class Main1 {
    static void main(String[] args) {
        PrisonerManager.initializePrisoners();
        PrisonerManager.execution();
    }
}
