import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Liquid {
    private String name;
    private int weight;
    private int totalValue;
    private double ratio ;

    public void initRatio () {
        ratio = totalValue / (double) weight;
    }

    public Liquid(String name, int weight, int totalValue) {
        this.name = name;
        this.weight = weight;
        this.totalValue = totalValue;
        initRatio();
    }
}


class Main2 {
    private static List<Liquid> liquids = new ArrayList<>();

    static void main(String[] args) {
        double totalWeight = 10;
        double totalValue = 0;
        Map<String, Double> Ingredients = new HashMap<>();
        liquids.add(new Liquid("水", 4, 24));
        liquids.add(new Liquid("牛奶", 8, 160));
        liquids.add(new Liquid("五粮液", 2, 4000));
        liquids.add(new Liquid("可乐", 6, 108));
        liquids.add(new Liquid("茅台", 1, 4000));
        liquids.sort((l1, l2) -> (int)(l2.getRatio() - l1.getRatio()));
        System.out.println("按比例降序：" + liquids);
        while (totalWeight > 0) {
            if(liquids.get(0).getWeight() > totalWeight) {
                totalValue += liquids.get(0).getRatio() * totalWeight;
                Ingredients.put(liquids.get(0).getName(), totalWeight);
                totalWeight = 0;
            } else {
                totalWeight -= liquids.get(0).getWeight();
                totalValue += liquids.get(0).getTotalValue();
                Ingredients.put(liquids.get(0).getName(), (double) liquids.get(0).getWeight());
                liquids.remove(0);
            }
        }
        System.out.println("总价值" + totalValue);
        System.out.println("配料：" + Ingredients);
    }
}
