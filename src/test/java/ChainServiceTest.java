import org.bjss.ChainService;
import org.bjss.basket.model.Basket;
import org.bjss.exceptions.NonExistencePropertiesFile;
import org.bjss.offer.discounts.DiscountOffer;
import org.bjss.offer.model.AppliedOffers;
import org.bjss.product.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ChainServiceTest {

    protected static final String PRODUCT_APPLE = "Apples";
    protected static final String PRODUCT_BREAD = "Bread";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    protected Basket data;

    @Mock
    protected Product apple, bread;
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    public void loadProperties_NonExistenceFileProperties_ThrownMessage(){
        List<AppliedOffers> appliedOffer = new ArrayList<>();


        Exception exception = assertThrows(NonExistencePropertiesFile.class, () -> {
            new ChainService(new DiscountOffer(appliedOffer),"NonExistenceFile");
        });
        assertTrue(exception.getMessage().contains("Problems loading file properties"));

    }

    @Test
    public void loadProperties_ExistenceFileProperties_ThrownMessage() throws NonExistencePropertiesFile {
        List<AppliedOffers> appliedOffer = new ArrayList<>();

        assertDoesNotThrow(() -> {
            new ChainService(new DiscountOffer(appliedOffer), "app.properties");
        });
    }

    @Test
    public void printOut_WhenAppliedOffers_ReturnsMessageWithAppliedOffers() throws NonExistencePropertiesFile {
        List<AppliedOffers> appliedOffer = new ArrayList<>();

        Map<Product, Integer> withApple = new HashMap<>() {{
            put(apple, 1);
            put(bread, 1);
        }};

        System.setOut(new PrintStream(outputStreamCaptor));
        when(apple.name()).thenReturn(PRODUCT_APPLE);
        when(bread.name()).thenReturn(PRODUCT_BREAD);
        when(apple.price()).thenReturn(new BigDecimal("1.00"));
        when(data.getProducts()).thenReturn(withApple);
        when(data.getTotal()).thenReturn(new BigDecimal("1.00"));
        ChainService chainService = new ChainService(new DiscountOffer(appliedOffer), "app.properties");

        chainService.printOut(data,appliedOffer);

        assertEquals("Subtotal: £1.00\n" +
                "Apples 10% off: -0.10p\n" +
                "Total: £0.90", outputStreamCaptor.toString().trim());

    }

    @Test
    public void printOut_WhenNoOffersApplied_ReturnResultWithANoOffers() throws NonExistencePropertiesFile {
        List<AppliedOffers> appliedOffer = new ArrayList<>();

        Map<Product, Integer> withApple = new HashMap<>() {{}};

        System.setOut(new PrintStream(outputStreamCaptor));
        when(data.getTotal()).thenReturn(new BigDecimal("1.00"));
        when(data.getProducts()).thenReturn(withApple);

        ChainService chainService = new ChainService(new DiscountOffer(appliedOffer), "app.properties");

        chainService.printOut(data,appliedOffer);

        assertEquals("Subtotal: £1.00\n" +
                "(No offers available)\n" +
                "Total: £1.00", outputStreamCaptor.toString().trim());

    }

}

