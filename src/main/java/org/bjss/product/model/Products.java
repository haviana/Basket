package org.bjss.product.model;

import java.util.LinkedList;

/**
 * Handles with the getting and adding products
 */
public class Products {

    LinkedList<Product> products = new LinkedList<>();
    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products=products;
    }
    public boolean addProduct(Product product) {
        return this.products.add(product) ;
    }
}
