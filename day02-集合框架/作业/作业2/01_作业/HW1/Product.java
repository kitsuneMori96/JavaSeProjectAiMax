package HW1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private String shopName;
    private String productName;

    @Override
    public int hashCode() {
        return Objects.hash(shopName, productName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(shopName, product.shopName) && Objects.equals(productName, product.productName);
    }

}

class ProductMap {
    private static HashMap<Product, Integer> productMap = new HashMap<>();

    public static HashMap<Product, Integer> gerProductMap() {
        return productMap;
    }

    public static void showProductMap() {
        System.out.println("商品列表：");
        for (Product product : ProductMap.gerProductMap().keySet()) {
            System.out.println(product.getShopName() + " - " + product.getProductName() + " - " + ProductMap.gerProductMap().get(product));
        }
    }
}

class ProductService {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入商店名称：,输入q退出");
            String shopName = scanner.nextLine();
            if (Objects.equals(shopName, "q")) {
                break;
            }
            System.out.println("请输入商品名称：");
            String productName = scanner.nextLine();
            Product product = new Product(shopName, productName);
            if(ProductMap.gerProductMap().containsKey(product)){
                ProductMap.gerProductMap().put(product, ProductMap.gerProductMap().get(product) + 1);
            }
            else {
                ProductMap.gerProductMap().put(product, 1);
            }
        }

        ProductMap.showProductMap();
        System.out.println("程序结束");
    }
}