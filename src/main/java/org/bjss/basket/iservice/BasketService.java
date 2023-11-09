package org.bjss.basket.iservice;

import org.bjss.basket.BasketSummary;
import org.bjss.product.exceptions.ProductNotFoundException;


/**
 * Service for consulting the basket
 */
public interface BasketService {

    /**
     * Return a basket
     * @return
     */
    public BasketSummary getBasket();

    /**
     * Find product by name and add to basket
     * @param product name of product
     * @throws ProductNotFoundException
     */
    public void addToBasket(String product) throws ProductNotFoundException;
}
