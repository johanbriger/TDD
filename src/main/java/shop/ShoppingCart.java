package shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private Map<Product, Integer> items = new HashMap<>();

    // Lägg till produkt

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    // Ta bort produkt

    public void removeProduct(Product product) {
        items.remove(product);
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



    public void updateQuantity(Product product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }if (quantity == 0)  {
            items.remove(product);
        }else  {
            items.put(product, quantity);
        }
    }

    private double discountPercentage = 0.0;

    public void applyDiscount(double percentage) {
        if(percentage > 100.0) {
            this.discountPercentage = 100.0;
        }else if (percentage < 0.0) {
            throw new IllegalArgumentException("Percentage cannot be negative");
        }else  {
            this.discountPercentage = percentage;
        }
    }

    public double calculateTotalDiscount() {
        double subtotal = 0;
        for (var entry : items.entrySet()) {
            subtotal += entry.getKey().price() * entry.getValue();
        }
        // Applicera rabatten
        return subtotal * (1 - (discountPercentage / 100));
    }



}
