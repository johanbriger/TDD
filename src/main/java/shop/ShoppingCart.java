package shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public double calculateTotal() {
        double total = 0;
        for (var entry : items.entrySet()) {
            total += entry.getKey().price() * entry.getValue();
        }
        return total;
    }

    public int getQuantity(Product product) {
        return items.getOrDefault(product, 0);
    }
}
