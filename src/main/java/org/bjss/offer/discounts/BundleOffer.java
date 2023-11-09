package org.bjss.offer.discounts;



import org.bjss.basket.model.Basket;
import org.bjss.offer.Handler;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Concrete discount that is responsible for the '2 tins of soup gives half price bread' rule
 */
public class BundleOffer extends Handler {

	private static final String PRODUCT_MATCH_SOUP = "Soup";

	private static final String PRODUCT_MATCH_BREAD = "Bread";
	
	public static final String OFFER_DESCRIPTION = "Buy 2 tins of soup, get loaf half price";

	protected final String productMatch;
	protected final  String productMatchToUpdate;

	protected final  String description;

	private final List<AppliedOffers> offers ;
	public BundleOffer(List<AppliedOffers> offers, String productMatch, String productMatchToUpdate, String description) {
		this.offers = offers;
		this.productMatch=productMatch;
		this.productMatchToUpdate= productMatchToUpdate;
		this.description = description;
	}


	public boolean isApplicable(Basket data) {
		Optional<Map.Entry<Product, Integer>> soupEntry = getEntry(data, productMatch);
		Optional<Map.Entry<Product, Integer>> breadEntry = getEntry(data, productMatchToUpdate);

		return breadEntry.isPresent() && soupEntry.isPresent() && soupEntry.get().getValue() >= 2;
	}
	
	@Override
	public AppliedOffers processData(Basket data) {
		Optional<Map.Entry<Product, Integer>> soupEntry = getEntry(data, productMatch);
		Optional<Map.Entry<Product, Integer>> breadEntry = getEntry(data, productMatchToUpdate);

		if (soupEntry.isPresent() && breadEntry.isPresent()) {
			int maxDiscounts = soupEntry.get().getValue() / 2;
			int numBreadEntries = breadEntry.get().getValue();
			int numDiscounts = Math.min(maxDiscounts, numBreadEntries);

			BigDecimal price = breadEntry.get().getKey().price();
						
			offerDetails.setDiscount(price.multiply(new BigDecimal(numDiscounts))
										  .divide(new BigDecimal(2)));
		}
		
		offerDetails.setOffer(description);
		
	    return offerDetails;
	}
	public boolean handle(Basket data) {
		return process(data,offers);
	}

}
