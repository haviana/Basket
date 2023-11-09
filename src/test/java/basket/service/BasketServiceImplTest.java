package basket.service;

import org.bjss.product.exceptions.ProductNotFoundException;
import org.bjss.basket.service.BasketServiceImpl;
import org.bjss.product.model.Product;
import org.bjss.product.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketServiceImplTest {

    ProductServiceImpl productService = new ProductServiceImpl();


    @BeforeEach
    public void setup() {
        productService.createProducts();
    }
    @Test()
    public void addToBasket_WithValidOneProduct_ProductAdded() throws ProductNotFoundException {

        BasketServiceImpl basketService = new BasketServiceImpl(productService);

        basketService.addToBasket("Soup");

        Product prooduct = new Product("Soup",BigDecimal.valueOf(0.65));

        assertTrue(basketService.getBasket().getProducts().containsKey(prooduct));
        assertEquals(basketService.getBasket().getTotal(), new BigDecimal("0.65"));
    }

    @Test()
    public void  addToBasket_WithValidTwoProducts_ProductMerged() throws ProductNotFoundException {

        BasketServiceImpl basketService = new BasketServiceImpl(productService);

        basketService.addToBasket("Soup");
        basketService.addToBasket("Soup");

        Product prooduct = new Product("Soup",BigDecimal.valueOf(0.65));

        assertTrue(basketService.getBasket().getProducts().containsKey(prooduct));
        assertEquals(2, (int) basketService.getBasket().getProducts().get(prooduct));
        assertEquals(basketService.getBasket().getTotal(), new BigDecimal("1.30"));
    }
}
