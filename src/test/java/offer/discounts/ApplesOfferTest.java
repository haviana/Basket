package offer.discounts;

import org.bjss.basket.model.Basket;
import org.bjss.offer.discounts.DiscountOffer;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ApplesOfferTest  {

    List<AppliedOffers> appliedOffer = new ArrayList<>();
    protected DiscountOffer appleOffer = new DiscountOffer(appliedOffer);

    protected static final String PRODUCT_APPLE = "Apples";
    protected static final String PRODUCT_BREAD = "Bread";
    protected static final String PRODUCT_SOUP = "Soup";


    @Mock
    protected Basket data;

    @Mock
    protected Product apple, bread, soup;
    @AfterEach
    public void tearDown() {
        Mockito.reset(data);
        Mockito.reset(apple);
        Mockito.reset(data);
        Mockito.reset(soup);
    }
    @Test
    public void isApplicable_WhenOneAppleOffer_ThenIsApplicable() {
        Map<Product, Integer> withApple = new HashMap<>() {{
            put(apple, 1);
            put(bread, 1);
        }};

        when(data.getProducts()).thenReturn(withApple);
        when(apple.name()).thenReturn(PRODUCT_APPLE);
        when(bread.name()).thenReturn(PRODUCT_BREAD);

        assertTrue(appleOffer.isApplicable(data));

    }

    @Test
    public void isApplicable_WhenNoApples_ThenItsNotApplicable() {
        Map<Product, Integer> withoutApple = new HashMap<>() {{
            put(soup, 1);
            put(bread, 1);
        }};

        when(data.getProducts()).thenReturn(withoutApple);
        when(soup.name()).thenReturn(PRODUCT_SOUP);
        when(bread.name()).thenReturn(PRODUCT_BREAD);

        Assertions.assertFalse(appleOffer.isApplicable(data));
    }

    @Test
    public void processData_() {
        Map<Product, Integer> withoutApple = new HashMap<>() {{
            put(soup, 1);
            put(bread, 1);
        }};
        when(data.getProducts()).thenReturn(withoutApple);
        when(soup.name()).thenReturn(PRODUCT_SOUP);
        when(bread.name()).thenReturn(PRODUCT_BREAD);


        AppliedOffers offerDetails = appleOffer.processData(data);

        Assertions.assertEquals(DiscountOffer.OFFER_DESCRIPTION, offerDetails.getOffer());
        Assertions.assertEquals(new BigDecimal("0"), offerDetails.getDiscount());
    }

    @Test
    public void processData_WhenTWOApplesOffer_ThenDiscountForBoth() {
        Map<Product, Integer> withMultipleApple = new HashMap<>() {{
            put(apple, 2);
            put(bread, 1);
        }};

        when(data.getProducts()).thenReturn(withMultipleApple);
        when(apple.name()).thenReturn(PRODUCT_APPLE);
        when(apple.price()).thenReturn(new BigDecimal("1.00"));

        when(bread.name()).thenReturn(PRODUCT_BREAD);



        AppliedOffers offerDetails = appleOffer.processData(data);

        Assertions.assertEquals(DiscountOffer.OFFER_DESCRIPTION, offerDetails.getOffer());
        Assertions.assertEquals(new BigDecimal("0.20"), offerDetails.getDiscount());
        assertTrue(appleOffer.isApplicable(data));

    }

}
