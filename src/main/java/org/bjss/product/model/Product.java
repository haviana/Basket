package org.bjss.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Stores a Product
 * @param name
 * @param price
 */
public record Product(@JsonProperty("name") String name, @JsonProperty("price") BigDecimal price) {
}
