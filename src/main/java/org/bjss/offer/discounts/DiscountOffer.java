package org.bjss.offer.discounts;



import org.bjss.basket.model.Basket;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.offer.Handler;
import org.bjss.product.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Concrete discount that is responsible for the '10% of apples' rule
 */
public class DiscountOffer extends Handler {

	protected  final String productMatch; //= "Apples";

	protected  final String description ;//= "Apples 10% off";
	protected  final BigDecimal discountPercent ;//= "Apples 10% off";

	private final List<AppliedOffers> offers ;
	public DiscountOffer(List<AppliedOffers> offers, String productMatch, String description, String discountPercent) {
		this.offers = offers;
		this.productMatch= productMatch;
		this.description= description;
		this.discountPercent= new BigDecimal(discountPercent);

	}

	public boolean handle(Basket data) {
		return process(data,offers);
	}
	
	public boolean isApplicable(Basket data) {
		return data.getProducts().keySet().stream().anyMatch(product -> product.name().equals(productMatch));
	}
	
	@Override
	public AppliedOffers processData(Basket data) {
		Optional<Map.Entry<Product, Integer>> entry = getEntry(data, productMatch);
		
		if (entry.isPresent()) {
	    	BigDecimal price = entry.get().getKey().price();
	    	BigDecimal count = new BigDecimal(entry.get().getValue());
	    	offerDetails.setDiscount(price.multiply(count).multiply(discountPercent).setScale(2));
		}
		
		offerDetails.setOffer(description);
		
	    return offerDetails;
	}
}
