package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart cart;
    private Product apple;
    private Product banana;

    @BeforeEach
    void setUp() {
        // Körs innan varje test för att nollställa tillståndet
        cart = new ShoppingCart();
        apple = new Product("1", "Äpple", 10.0);
        banana = new Product("2", "Banan", 20.0);
    }

    // --- Grundläggande funktionalitet ---

    @Test
    void shouldReturnZeroTotalForEmptyCart() {
        assertEquals(0.0, cart.calculateTotal(), "En tom korg ska ha totalpris 0");
    }

    @Test
    void shouldAddProductAndCalculateTotal() {
        cart.addProduct(apple);
        assertEquals(10.0, cart.calculateTotal(), "Totalen ska matcha priset på en vara");
    }

    @Test
    void shouldCalculateTotalForMultipleDifferentProducts() {
        cart.addProduct(apple);   // 10 kr
        cart.addProduct(banana);  // 20 kr
        assertEquals(30.0, cart.calculateTotal());
    }

    // --- Kvantitetshantering ---

    @Test
    void shouldIncreaseQuantityWhenAddingExistingProduct() {
        cart.addProduct(apple);
        cart.addProduct(apple);

        assertEquals(2, cart.getQuantity(apple), "Antalet ska vara 2");
        assertEquals(20.0, cart.calculateTotal(), "Totalen ska vara 2 * styckpris");
    }

    @Test
    void shouldUpdateQuantityDirectly() {
        cart.addProduct(apple);
        cart.updateQuantity(apple, 5); // Ändra från 1 till 5

        assertEquals(5, cart.getQuantity(apple));
        assertEquals(50.0, cart.calculateTotal());
    }

    // --- Borttagning av varor ---

    @Test
    void shouldRemoveProductCompletely() {
        cart.addProduct(apple);
        cart.removeProduct(apple);

        assertEquals(0, cart.getQuantity(apple));
        assertEquals(0.0, cart.calculateTotal());
    }

    @Test
    void shouldRemoveProductWhenQuantitySetToZero() {
        cart.addProduct(apple);
        cart.updateQuantity(apple, 0); // Att sätta 0 ska ta bort varan

        assertEquals(0, cart.getQuantity(apple));
        assertFalse(cart.calculateTotal() > 0);
    }

    // --- Rabatter ---

    @Test
    void shouldApplyDiscountPercentage() {
        cart.addProduct(apple); // 100 kr (om vi låtsas att det är dyra äpplen)
        Product expensiveApple = new Product("3", "Guldäpple", 100.0);

        ShoppingCart expensiveCart = new ShoppingCart();
        expensiveCart.addProduct(expensiveApple);

        // 20% rabatt på 100 kr = 80 kr totalt
        expensiveCart.applyDiscount(20.0);

        assertEquals(80.0, expensiveCart.calculateTotal(), 0.001);
    }

    //  Edge cases

    @Test
    void shouldThrowExceptionForNegativeQuantityUpdate() {
        cart.addProduct(apple);

        // Vi förväntar oss ett undantag (Exception)
        assertThrows(IllegalArgumentException.class, () -> {
            cart.updateQuantity(apple, -5);
        }, "Ska inte gå att sätta negativt antal");
    }

    @Test
    void shouldThrowExceptionForNegativeDiscount() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.applyDiscount(-10.0);
        });
    }

    @Test
    void shouldCapDiscountAt100Percent() {
        cart.addProduct(apple);

        // Om vi försöker ge 150% rabatt ska det hanteras som 100% (gratis)
        cart.applyDiscount(150.0);

        assertEquals(0.0, cart.calculateTotal(), "Priset kan inte bli lägre än 0");
    }
}