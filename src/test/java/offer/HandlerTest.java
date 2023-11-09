package offer;

import org.bjss.basket.model.Basket;
import org.bjss.offer.Handler;
import org.bjss.offer.discounts.DiscountOffer;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HandlerTest extends BaseAppliedOffersTest{

    @Mock
    protected Basket data;

    @Mock
    protected Product apple, bread, soup;

    List<AppliedOffers> appliedOffers = new ArrayList<>();
    @Test
    void process_WhenHasApples_ThenAddNewOffer() {

        Handler baseOffer = new DiscountOffer(appliedOffers);

        Map<Product, Integer> withApple = new HashMap<>() {{
            put(apple, 1);
            put(bread, 1);
        }};

        when(data.getProducts()).thenReturn(withApple);
        when(apple.name()).thenReturn(PRODUCT_APPLE);
        when(apple.price()).thenReturn(new BigDecimal("1.00"));

        baseOffer.process(data,appliedOffers);

        Assertions.assertFalse(appliedOffers.isEmpty());

    }

}
