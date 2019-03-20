package pl.switalla.allert.alert;

import org.junit.Test;
import pl.switalla.allert.product.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertTypesTest {

    @Test
    public void shouldTriggerPercentAlert() {
        Product product = Product.builder()
                .name("Test Product")
                .price(BigDecimal.valueOf(1000))
                .build();

        Alert alert = Alert.builder()
                .discount(BigDecimal.valueOf(10))
                .discountType(DiscountType.PERCENT)
                .product(product)
                .build();

        assertThat(AlertTypes.isTriggered(BigDecimal.valueOf(899), alert)).isTrue();
    }

}