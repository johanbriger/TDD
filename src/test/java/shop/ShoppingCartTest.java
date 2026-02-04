package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    // Add to Cart and calculate price

    @Test
    void shouldReturnZeroTotalForEmptyCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertEquals(0.0, shoppingCart.calculateTotal());
    }

    @Test
    void shouldAddProductAndCalculateTotalPrice() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product apple = new Product("1","Äpple", 10.0);
        shoppingCart.addProduct(apple);
        assertEquals(10.0, shoppingCart.calculateTotal());
    }



    @Test
    void shouldIncreaseQuantityWhenAddingExistingProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product apple = new Product("1", "Äpple", 10.0);

        shoppingCart.addProduct(apple);
        shoppingCart.addProduct(apple);

        assertEquals(20.0, shoppingCart.calculateTotal());
        assertEquals(2, shoppingCart.getQuantity(apple));
    }

    @Test
    void shouldRemoveProductCompletelyFromCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product apple = new Product("1","Äpple", 10.0);
        shoppingCart.addProduct(apple);

        shoppingCart.removeProduct(apple);

        assertEquals(0.0, shoppingCart.getQuantity(apple));
        assertEquals(0.0, shoppingCart.calculateTotal());
    }

    @Test
    void shouldUpdateQuantity() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product apple = new Product("1","Äpple", 10.0);

        shoppingCart.updateQuantity(apple, 5);

        assertEquals(5, shoppingCart.getQuantity(apple));
        assertEquals(50.0, shoppingCart.calculateTotal());

    }

    @Test
    void shouldApplyDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product apple = new Product("1","Äpple", 100.0);
        shoppingCart.addProduct(apple);

        shoppingCart.applyDiscount(20.0);

        assertEquals(80.0, shoppingCart.calculateTotalDiscount());
    }

}