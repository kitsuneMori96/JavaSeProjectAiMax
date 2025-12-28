import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@Data

public class Room {
    private static ArrayList<Card> allCards = new ArrayList<Card>();

    {
        String[] colors = {"♥","♠","♣","♦"};
        String[] sizes = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        for(String color : colors){
            for(String size : sizes){
                Card card = new Card(size,color);
                allCards.add(card);
            }
        }
        Collections.addAll(allCards,new Card("","大王"),new Card("","小王"));
    }

    public static void newRoom(){
        Room room = new Room();
        Collections.shuffle(allCards);
        System.out.println(allCards);
        Map<String, ArrayList<Card>> players = new HashMap<>();
        ArrayList<Card> player1 = new ArrayList<>();
        ArrayList<Card> player2 = new ArrayList<>();
        ArrayList<Card> player3 = new ArrayList<>();
        players.put("player1",player1);
        players.put("player2",player2);
        players.put("player3",player3);

        for(int i =0; i<51; i++){
            int index = i%3;
            switch (index){
                case 0:
                    player1.add(allCards.get(i));
                    break;
                case 1:
                    player2.add(allCards.get(i));
                    break;
                case 2:
                    player3.add(allCards.get(i));
                    break;
            }
        }

        List<Card> publicCards = allCards.subList(51,allCards.size());
        System.out.println("publicCards:"+publicCards);

        Collections.sort(player1);
        Collections.sort(player2);
        Collections.sort(player3);

        System.out.println("player1:"+player1);
        System.out.println("player2:"+player2);
        System.out.println("player3:"+player3);

        for(Map.Entry<String, ArrayList<Card>> entry : players.entrySet()){
            System.out.println("-------------------");
            String playerName = entry.getKey();
            ArrayList<Card> playerCards = entry.getValue();
            System.out.println(playerName+":"+playerCards);
        }
    }

    private static ArrayList<Card> getAllCards() {
        return allCards;
    }

    static void main(String[] args) {
        newRoom();
    }
}
