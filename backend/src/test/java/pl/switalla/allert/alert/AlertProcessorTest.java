package pl.switalla.allert.alert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.switalla.allert.product.Product;
import pl.switalla.allert.product.ProductPriceRetriever;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlertProcessorTest {

    @Mock
    private ProductPriceRetriever priceRetriever;

    @Mock
    private AlertDispatcher alertDispatcher;

    @InjectMocks
    private AlertProcessor processor;

    @Test
    public void shouldDispatchAlert() {
        when(priceRetriever.retrieve(any(Product.class))).thenReturn(BigDecimal.valueOf(899));

        Product product = Product.builder()
                .name("Test Product")
                .price(BigDecimal.valueOf(1000))
                .build();

        Alert alert = Alert.builder()
                .discount(BigDecimal.valueOf(10))
                .discountType(DiscountType.PERCENT)
                .product(product)
                .build();

        processor.process(alert);

        verify(alertDispatcher, times(1)).dispatch(eq(alert));

    }
}