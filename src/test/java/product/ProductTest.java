package product;

import org.bjss.product.exceptions.ProductNotFoundException;
import org.bjss.product.model.Product;
import org.bjss.product.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {

    @Test()
    public void findProductByName_NonExistenceProduct_ThrownMessage(){

        ProductServiceImpl productService = new ProductServiceImpl();

        productService.createProduct(new Product("Soap", BigDecimal.valueOf(0.65)));

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.findProductbyName("Soap1");
        });

        assertTrue(exception.getMessage().contains("No such product: "));

    }

    @Test()
    public void findProductByName_ExistenceProduct_ReturnsProduct() throws ProductNotFoundException {

        ProductServiceImpl productService = new ProductServiceImpl();

        Product soap = new Product("Soap", BigDecimal.valueOf(0.65));
        productService.createProduct(soap);

        assertDoesNotThrow(()-> {
            productService.findProductbyName(soap.name());
        });

        assertEquals(productService.findProductbyName(soap.name()), soap);

    }
}
