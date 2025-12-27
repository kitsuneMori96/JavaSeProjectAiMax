package HW4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data

public class Oder {
    private String name;
    private double price;
    private int id;

}

class OderItem {
    private ArrayList<Oder> oders;

    public OderItem() {
        oders = new ArrayList<Oder>();
    }

    public void addOder(Oder oder) {
        oders.add(oder);
    }

    public ArrayList<Oder> getOders() {
        return oders;
    }
}

class Operator {
    public static void main(String[] args) {
        ArrayList<OderItem> oderItems = new ArrayList<OderItem>();
        OderItem oderItem1 = new OderItem();
        OderItem oderItem2 = new OderItem();
        Oder oder1 = new Oder("apple", 1.5, 1);
        Oder oder2 = new Oder("banana", 2.5, 2);
        Oder oder3 = new Oder("orange", 3.5, 3);
        Oder oder4 = new Oder("pear", 4.5, 4);
        Oder oder5 = new Oder("grape", 5.5, 5);
        oderItem1.addOder(oder1);
        oderItem1.addOder(oder2);
        oderItem2.addOder(oder3);
        oderItem2.addOder(oder4);
        oderItem2.addOder(oder5);
        oderItems.add(oderItem1);
        oderItems.add(oderItem2);
        for (OderItem oderItem : oderItems) {
            System.out.println("---------------------");
            for (Oder oder : oderItem.getOders()) {
                System.out.println(oder.getName() + " " + oder.getPrice() + " " + oder.getId());
            }
        }
    }
}