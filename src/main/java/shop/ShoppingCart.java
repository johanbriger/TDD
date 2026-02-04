package shop;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();
    private double discountPercentage = 0.0;


    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }


    public void removeProduct(Product product) {
        items.remove(product);
    }


    public void updateQuantity(Product product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Kvantitet kan inte vara negativ");
        }
        if (quantity == 0) {
            removeProduct(product);
        } else {
            items.put(product, quantity);
        }
    }


    public int getQuantity(Product product) {
        return items.getOrDefault(product, 0);
    }


    public void applyDiscount(double percentage) {
        if (percentage < 0) {
            throw new IllegalArgumentException("Rabatt kan inte vara negativ");
        }
        this.discountPercentage = Math.min(percentage, 100.0);
    }


    public double calculateTotal() {
        double subtotal = items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().price() * entry.getValue())
                .sum();

        return subtotal * (1 - (discountPercentage / 100));
    }
}