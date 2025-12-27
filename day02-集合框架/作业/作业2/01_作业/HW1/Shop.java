package HW1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop {
    private static HashMap<String, Integer> products = new HashMap<>();

    static void main(String[] args) {
        Shop shop = new Shop();
        System.out.println("Welcome to our shop!");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("输入商品");
            String product = sc.next();
            if (product.equals("end")) {
                break;
            }
            if (products.containsKey(product)) {
                products.put(product, products.get(product) + 1);
            }
            else products.put(product, 1);
        }
        System.out.println("-------------------------");
        for(String key : products.keySet()){
            System.out.println(key+" : "+products.get(key));
        }
    }
}
