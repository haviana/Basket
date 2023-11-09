package org.bjss.product.iservice;

import org.bjss.product.exceptions.ProductNotFoundException;
import org.bjss.product.model.Product;
import org.bjss.product.model.Products;

/**
 * Service that provides methods for creating and find products
 */
public interface ProductService {

    public void createProduct(Product product);

    public void createProducts();

    public Product findProductbyName(String productName) throws ProductNotFoundException;

    public void setProducts(Products products);

}
