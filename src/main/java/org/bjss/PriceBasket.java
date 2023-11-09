package org.bjss;

import org.bjss.basket.service.BasketServiceImpl;
import org.bjss.exceptions.NonExistencePropertiesFile;
import org.bjss.offer.Handler;
import org.bjss.offer.discounts.DiscountOffer;
import org.bjss.offer.discounts.BundleOffer;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Products;
import org.bjss.product.service.ProductServiceImpl;
import org.bjss.utils.LoadFromJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class PriceBasket {

    private static final List<AppliedOffers> appliedOffers = new ArrayList<>();
    private static final Handler baseOffer = new DiscountOffer(appliedOffers,"Apples","Apples 10% off","0.1");
    private static final Logger logger = Logger.getLogger(PriceBasket.class.getName());
    private static final String filePropertiesPath = "app.properties";
    private static final String filePathJson = "src/main/resources/Products.json";
    public static void main(String[] args) {
        ChainService chainService;
        ProductServiceImpl productService = new ProductServiceImpl();
        BasketServiceImpl basketService = new BasketServiceImpl(productService);

        try {
            if (args.length > 0) {
                chainService = new ChainService(baseOffer, filePropertiesPath);

                baseOffer.setNextHandler(new BundleOffer(appliedOffers, "Soup", "Bread", "Buy 2 tins of soup, get loaf half price"));
                logger.info("Adding Products");
                Products products = new LoadFromJson().loadProductsFromJsonFile(filePathJson);
                if (products != null)
                    productService.setProducts(products);
                else
                    productService.createProducts();

                Arrays.stream(args).forEach(basketService::addToBasket);
                chainService.printOut(basketService.getBasket(), appliedOffers);

            } else {
                logger.info("No items to add to basket");
            }
        } catch (NonExistencePropertiesFile e) {
            logger.warning(e.getMessage());
        }

    }
}

