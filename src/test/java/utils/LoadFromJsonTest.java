package utils;

import org.bjss.basket.model.Basket;
import org.bjss.exceptions.NonExistencePropertiesFile;
import org.bjss.product.model.Product;
import org.bjss.product.model.Products;
import org.bjss.utils.LoadFromJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class LoadFromJsonTest {

    @Mock
    protected Basket data;

    @Mock
    protected Product apple, bread;

    @Test
    public void loadFromJson_WhenAFileDoesNotExist_ShouldReturnNull() throws NonExistencePropertiesFile {
        LoadFromJson jsonData = new LoadFromJson();

        Products products = jsonData.loadProductsFromJsonFile("src/test/resources/Product.json");

        assertNull(products);

    }

    @Test
    public void loadFromJson_WhenNoOffersApplied_ReturnResultWithANoOffers() throws NonExistencePropertiesFile {
        LoadFromJson jsonData = new LoadFromJson();

        Products expectedProducts = new Products();
        expectedProducts.addProduct(new Product("Soup", BigDecimal.valueOf(0.65)));
        expectedProducts.addProduct(new Product("Bread", BigDecimal.valueOf(0.80)));
        expectedProducts.addProduct(new Product("Milk", BigDecimal.valueOf(1.30)));
        expectedProducts.addProduct(new Product("Apples", BigDecimal.valueOf(1.00)));

        Products products = jsonData.loadProductsFromJsonFile("src/test/resources/Products.json");

        assertEquals(products.getProducts().get(0).name(),expectedProducts.getProducts().get(0).name());
        assertEquals(products.getProducts().get(1).name(),expectedProducts.getProducts().get(1).name());
        assertEquals(products.getProducts().get(2).name(),expectedProducts.getProducts().get(2).name());

    }
}
