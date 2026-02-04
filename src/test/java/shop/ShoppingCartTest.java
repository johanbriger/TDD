package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void shouldReturnZeroTotalForEmptyCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertEquals(0.0, shoppingCart.calculateTotal());
    }

    @Test
    void shouldAddProductAndCalculateTotal() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product apple = new Product("1","Äpple", 10.0);
        shoppingCart.addProduct(apple);
        assertEquals(10.0, shoppingCart.calculateTotal());
    }

}