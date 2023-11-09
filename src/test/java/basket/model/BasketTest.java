package basket.model;

import org.bjss.basket.model.Basket;
import org.bjss.product.model.Product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketTest {
    private Basket basket = new Basket();

    @Mock
    Product apple, bread;

    @Test
    public void putProducts_WhenAddingOneProduct_ThenAProductShouldBeAdded() {
        when(apple.price()).thenReturn(new BigDecimal("1.00"));

        basket.putProduct(apple);

        Assertions.assertEquals(new BigDecimal("1.00"), basket.getTotal());
        Assertions.assertEquals(1, basket.getProducts().size());
    }

    @Test
    public void putProducts_WhenAddingTwoProducts_ThenAProductShouldBeAdded() {
        when(bread.price()).thenReturn(new BigDecimal("0.80"));
        when(apple.price()).thenReturn(new BigDecimal("1.00"));

        basket.putProduct(apple);
        basket.putProduct(bread);

        Assertions.assertEquals(new BigDecimal("1.80"), basket.getTotal());
        Assertions.assertEquals(basket.getProducts().size(), 2);

    }

    @Test
    public void putProducts_WhenAddingRepeatedProduct_ThenProductShouldBeMerged() {
        when(bread.price()).thenReturn(new BigDecimal("0.80"));
        when(apple.price()).thenReturn(new BigDecimal("1.00"));

        basket.putProduct(apple);
        basket.putProduct(bread);
        basket.putProduct(apple);

        Assertions.assertEquals(new BigDecimal("2.80"), basket.getTotal());
        Assertions.assertEquals(basket.getProducts().size(), 2);

    }
}
