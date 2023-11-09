package org.bjss.basket.model;

import org.bjss.basket.BasketSummary;
import org.bjss.product.model.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Basket implements BasketSummary {
    private final Map<Product, Integer> products = new HashMap<>();

    private BigDecimal total;


    /**
     * Add product to basket and re-calculate total
     *
     * @param product to add
     */
    public void putProduct(Product product) {

        products.merge(product, 1, Integer::sum);
        total = calculateTotal();
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Calculate total price, sum of product price * product count
     *
     * @return total price of products
     */
    private BigDecimal calculateTotal() {
        return products
                .entrySet()
                .stream()
                .map(e -> e.getKey().price().multiply(new BigDecimal(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
