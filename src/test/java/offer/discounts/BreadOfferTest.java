package offer.discounts;

import offer.BaseAppliedOffersTest;
import org.bjss.offer.discounts.BundleOffer;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BreadOfferTest extends BaseAppliedOffersTest {

    @AfterEach
    public void tearDown() {
        Mockito.reset(data);
        Mockito.reset(apple);
        Mockito.reset(soup);
        Mockito.reset(bread);
    }

    @Test
    public void isApplicable_WhenContainsBread_ThenApplicableIsTrue() {
        HashMap<Product, Integer> withBreadAndSoup = new HashMap<>() {{
            put(apple, 1);
            put(bread, 1);
            put(soup, 2);
        }};

        when(soup.name()).thenReturn(PRODUCT_SOUP);
        when(bread.name()).thenReturn(PRODUCT_BREAD);
        when(apple.name()).thenReturn(PRODUCT_APPLE);
        when(data.getProducts()).thenReturn(withBreadAndSoup);
        Assertions.assertTrue(breadOffer.isApplicable(data));
    }

    @Test
    public void isApplicable_WhenNoContainBread_ThenApplicableIsFalse() {
        HashMap<Product, Integer> withoutBreadAndSoup = new HashMap<>() {{
            put(apple, 1);
        }};

        when(data.getProducts()).thenReturn(withoutBreadAndSoup);
        when(apple.name()).thenReturn(PRODUCT_APPLE);

        Assertions.assertFalse(breadOffer.isApplicable(data));
    }

    @Test
    public void processData_WhenContainsAllProducts_ThenOfferMustBeApplied() {
        HashMap<Product, Integer> withAppleBreadAndSoup = new HashMap<>() {{
            put(apple, 1);
            put(bread, 1);
            put(soup, 2);
        }};

        when(data.getProducts()).thenReturn(withAppleBreadAndSoup);
        when(apple.name()).thenReturn(PRODUCT_APPLE);
        when(bread.name()).thenReturn(PRODUCT_BREAD);
        when(soup.name()).thenReturn(PRODUCT_SOUP);

        when(apple.price()).thenReturn(new BigDecimal("1.00"));
        when(bread.price()).thenReturn(new BigDecimal("0.80"));
        when(soup.price()).thenReturn(new BigDecimal("0.60"));

        AppliedOffers offerDetails = breadOffer.processData(data);

        Assertions.assertEquals(BundleOffer.OFFER_DESCRIPTION, offerDetails.getOffer());
        Assertions.assertEquals(new BigDecimal("0.40"), offerDetails.getDiscount());
    }

    @Test
    public void processData_WhenNotContainBreadAndSoup_ThenNoDiscountForBread() {

        HashMap<Product, Integer> withoutBreadAndSoup = new HashMap<>() {{
            put(apple, 1);
        }};

        when(data.getProducts()).thenReturn(withoutBreadAndSoup);
        when(apple.name()).thenReturn(PRODUCT_APPLE);
        when(apple.price()).thenReturn(new BigDecimal("1.00"));
        AppliedOffers offerDetails = breadOffer.processData(data);

        Assertions.assertEquals(BundleOffer.OFFER_DESCRIPTION, offerDetails.getOffer());
        Assertions.assertEquals(new BigDecimal("0"), offerDetails.getDiscount());
    }
}
