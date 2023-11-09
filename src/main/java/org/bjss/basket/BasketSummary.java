package org.bjss.basket;

import org.bjss.product.model.Product;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Info about the basket regarding products and prices accumulation
 */
public interface BasketSummary {
    /**
     * Return products in container
     * @return map of products and counts
     */
    public Map<Product, Integer> getProducts();

    /**
     * Return total price of products in container
     * @return
     */
    public BigDecimal getTotal();
}
