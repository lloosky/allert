package pl.switalla.pricealert.alert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import pl.switalla.pricealert.product.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class AlertTypesParameterizedTest {

    private BigDecimal productBasePrice;
    private BigDecimal productCurrentPrice;
    private BigDecimal discount;
    private DiscountType discountType;
    private boolean expected;

    public AlertTypesParameterizedTest(BigDecimal productBasePrice, BigDecimal productCurrentPrice, BigDecimal discount, DiscountType discountType, boolean expected) {
        this.productBasePrice = productBasePrice;
        this.productCurrentPrice = productCurrentPrice;
        this.discount = discount;
        this.discountType = discountType;
        this.expected = expected;
    }

    @Parameters(name = "Baza={0}, Cena={1}, Rabat={2}, Typ={3}, Alert={4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {BigDecimal.valueOf(1000), BigDecimal.valueOf(900), BigDecimal.valueOf(100), DiscountType.AMOUNT, true},
                {BigDecimal.valueOf(1000), BigDecimal.valueOf(899), BigDecimal.valueOf(100), DiscountType.AMOUNT, true},
                {BigDecimal.valueOf(1000), BigDecimal.valueOf(901), BigDecimal.valueOf(100), DiscountType.AMOUNT, false},
                {BigDecimal.valueOf(1000), BigDecimal.valueOf(900), BigDecimal.valueOf(10), DiscountType.PERCENT, true},
                {BigDecimal.valueOf(1000), BigDecimal.valueOf(899), BigDecimal.valueOf(10), DiscountType.PERCENT, true},
                {BigDecimal.valueOf(1000), BigDecimal.valueOf(901), BigDecimal.valueOf(10), DiscountType.PERCENT, false}
        });
    }

    @Test
    public void shouldTriggerAmountAlert() {
        Product product = Product.builder()
                .name("Test Product")
                .price(productBasePrice)
                .build();

        Alert alert = Alert.builder()
                .discount(discount)
                .discountType(discountType)
                .product(product)
                .build();

        assertThat(AlertTypes.isTriggered(productCurrentPrice, alert)).isEqualTo(expected);
    }
}