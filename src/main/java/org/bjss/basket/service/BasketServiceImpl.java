package org.bjss.basket.service;


import org.bjss.product.exceptions.ProductNotFoundException;
import org.bjss.basket.model.Basket;
import org.bjss.basket.iservice.BasketService;
import org.bjss.product.model.Product;
import org.bjss.product.iservice.ProductService;

import java.util.logging.Logger;


public class BasketServiceImpl implements BasketService {

    private final ProductService productService;

    Logger logger = Logger.getLogger(BasketServiceImpl.class.getName());

    private final Basket basket;

    public BasketServiceImpl(ProductService products) {
        this.productService = products;
        this.basket = new Basket();
    }

    @Override
    public Basket getBasket() {
        return basket;
    }

    @Override
    public void addToBasket(String productName) {
        Product product = null;
        try {
            product = productService.findProductbyName(productName);
            getBasket().putProduct(product);
        } catch (ProductNotFoundException e) {
            logger.warning("No such product: " + e.getMessage());
        }

    }

}

