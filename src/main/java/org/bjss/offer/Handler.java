package org.bjss.offer;

import org.bjss.basket.model.Basket;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Product;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * A simple implementation of the chain of responsibility pattern to handle
 * discounts, each discount is processed and then calls next in chain. The results
 * are passed back up the chain to the handler.
 */
public abstract class Handler {

    protected Handler next;

    protected AppliedOffers offerDetails = new AppliedOffers();

    /**
     * Set the Next discount to be processed
     *
     * @param handler offer
     */
    public Handler setNextHandler(Handler handler) {
        this.next = handler;
        return this.next;
    }


    /**
     * If it contains next discount in the chain
     * @param handler
     * @return
     */
    public boolean handleNext(Basket handler) {
        if (next == null) {
            return true;
        }

        return next.handle( handler);
    }

    /**
     * Process every discount on the basket if it is applicable
     * @param data
     * @param offers
     * @return
     */
    public boolean process(Basket data, List<AppliedOffers> offers){
        if (data != null && !data.getProducts().isEmpty()) {
            if (isApplicable(data)) {
                offers.add(processData(data));
            }
        }
        return handleNext(data);
    }

    public abstract boolean handle(Basket handle);


    /**
     * Find the entry in the container for the named product, that entry contains
     * the product and a product count.
     *
     * @param data product container (basket)
     * @param name of product
     * @return Entry containing product and count
     */
    protected Optional<Entry<Product, Integer>> getEntry(Basket data, String name) {
        return data.getProducts().entrySet()
                .stream()
                .filter(e -> e.getKey().name().equals(name)).findFirst();
    }

    /**
     * Test if the discount can be applied to the product container
     *
     * @param data product container (basket)
     * @return true if applicable
     */
    abstract protected boolean isApplicable(Basket data);

    /**
     * Process the discount and calculate any discount
     *
     * @param data product container (basket)
     * @return details of any discount
     */
    abstract protected AppliedOffers processData(Basket data);
}
