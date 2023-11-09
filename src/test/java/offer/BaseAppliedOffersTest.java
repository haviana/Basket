package offer;


import org.bjss.basket.model.Basket;
import org.bjss.offer.discounts.DiscountOffer;
import org.bjss.offer.discounts.BundleOffer;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Product;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class BaseAppliedOffersTest {

    List<AppliedOffers> appliedOffer = new ArrayList<>();
    protected DiscountOffer appleOffer = new DiscountOffer(appliedOffer);
    protected BundleOffer breadOffer = new BundleOffer(appliedOffer);

    protected static final String PRODUCT_APPLE = "Apples";
    protected static final String PRODUCT_BREAD = "Bread";
    protected static final String PRODUCT_SOUP = "Soup";


    @Mock
    protected Basket data;

    @Mock
    protected Product apple, bread, soup;
}
