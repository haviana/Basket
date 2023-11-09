package org.bjss.product.service;

import org.bjss.product.exceptions.ProductNotFoundException;
import org.bjss.product.model.Product;
import org.bjss.product.iservice.ProductService;
import org.bjss.product.model.Products;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Logger;

public class ProductServiceImpl implements ProductService {

    Products products = new Products();

    Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    @Override
    public void createProduct(Product product) {
        if (!products.addProduct(product))
            logger.warning("Error adding product "+product.name());

    }

    @Override
    public void createProducts() {
        createProduct(new Product("Soup", BigDecimal.valueOf(0.65)));
        createProduct(new Product("Bread", BigDecimal.valueOf(0.80)));
        createProduct(new Product("Milk", BigDecimal.valueOf(1.30)));
        createProduct(new Product("Apples", BigDecimal.valueOf(1.0)));
    }

    @Override
    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public Product findProductbyName(String productName) throws ProductNotFoundException {

        if (productName == null || products.getProducts().stream().noneMatch(x-> Objects.equals(productName, x.name())))
            throw new ProductNotFoundException("No such product: "+productName);
        return products.getProducts().stream().filter(x-> Objects.equals(productName, x.name())).findFirst().get();

    }
}
