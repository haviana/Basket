package org.bjss;

import org.bjss.basket.model.Basket;
import org.bjss.exceptions.NonExistencePropertiesFile;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.offer.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This class is responsible for printing out the end result and start the state machine for the filter.
 * Merge all the basket with the existent products
 * and apply all the discounts available
 */
public class ChainService {

    private final Handler handler;
    private final Locale locale;
    private final String region = "region";
    private final String language = "language";

    public ChainService(Handler handler, String propertiesPath) throws NonExistencePropertiesFile {

        this.handler = handler;
        Properties properties;
        properties = loadProperties(propertiesPath);
        locale = new Locale.Builder().setRegion(properties.getProperty(region)).setLanguage(properties.getProperty(language)).build();
    }

    public void printOut(Basket basket, List<AppliedOffers> offers) {
        if (handler.handle(basket)) {

            BigDecimal subtotal = basket.getTotal();
            BigDecimal totalDiscounts = BigDecimal.ZERO;
            BigDecimal total = subtotal;

            System.out.printf("%s%s\n", "Subtotal: ", NumberFormat.getCurrencyInstance(locale).format(subtotal));

            if (offers == null || offers.isEmpty()) {
                System.out.printf("%s\n", "(No offers available)");
            } else {
                totalDiscounts = offers.stream().map(AppliedOffers::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);
                total = subtotal.subtract(totalDiscounts);
                offers.forEach(o -> System.out.printf("%s%s%s\n", o.getOffer(), ": -", o.getDiscount() + "p"));
            }

            System.out.printf("%s%s\n", "Total: ", NumberFormat.getCurrencyInstance(Locale.UK).format(total));

        }
    }

    public Properties loadProperties(String resourceFileName) throws NonExistencePropertiesFile {
        Properties configuration;
        InputStream inputStream = PriceBasket.class
                .getClassLoader()
                .getResourceAsStream(resourceFileName);
        if (inputStream == null) {
            throw new NonExistencePropertiesFile("Problems loading file properties");
        }
        try {
            configuration = new Properties();
            configuration.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new NonExistencePropertiesFile("Problems loading file properties");
        }

        return configuration;
    }
}
