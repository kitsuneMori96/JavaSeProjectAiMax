import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Card implements Comparable<Card> {
    private String size;
    private String color;

    @Override
    public String toString() {
        return size + " " + color;
    }

    @Override
    public int compareTo(Card card) {
        if (card == null) {
            return 1;
        }
        if (this == card) return 0;
        int size1, size2;
        if (color.equals("大王")){
            size1 = 17;
        } else if (color.equals("小王")){
            size1 = 16;
        } else if (size.equals("2")){
            size1 = 15;
        } else if (size.equals("A")) {
            size1 = 14;
        } else if (size.equals("K")) {
            size1 = 13;
        } else if (size.equals("Q")) {
            size1 = 12;
        } else if (size.equals("J")) {
            size1 = 11;
        } else {
            size1 = Integer.parseInt(size);
        }
        if (card.color.equals("大王")){
            size2 = 17;
        } else if (card.color.equals("小王")){
            size2 = 16;
        } else if (card.size.equals("2")){
            size2 = 15;
        } else if (card.size.equals("A")) {
            size2 = 14;
        } else if (card.size.equals("K")) {
            size2 = 13;
        } else if (card.size.equals("Q")) {
            size2 = 12;
        } else if (card.size.equals("J")) {
            size2 = 11;
        } else {
            size2 = Integer.parseInt(card.size);
        }
        if (size1 > size2) {
            return 1;
        } else if (size1 < size2) {
            return -1;
        } else {
            return switch (color) {
                case "♠", "♦" -> -1;
                default -> 1;
            };
        }
    }
}