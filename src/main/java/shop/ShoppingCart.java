package shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.price();
        }
        return total;
    }
}
