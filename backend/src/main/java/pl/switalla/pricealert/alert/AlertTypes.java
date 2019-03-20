package pl.switalla.pricealert.alert;

import lombok.extern.slf4j.Slf4j;
import pl.switalla.pricealert.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
class AlertTypes {
    private static final Collection<PriceAlert> ALERT_TYPES = new ArrayList<>(2);

    static {
        ALERT_TYPES.add((currentProductPrice, alert) -> {
            if (alert.getDiscountType() == DiscountType.AMOUNT) {
                Product product = alert.getProduct();
                return product.getPrice().subtract(currentProductPrice).doubleValue() >= alert.getDiscount().doubleValue();
            }
            return false;
        });

        ALERT_TYPES.add((currentProductPrice, alert) -> {
            if (alert.getDiscountType() == DiscountType.PERCENT) {
                Product product = alert.getProduct();
                return product.getPrice()
                        .subtract(currentProductPrice)
                        .divide(product.getPrice(), 5, RoundingMode.CEILING)
                        .multiply(BigDecimal.valueOf(100)).doubleValue() >= alert.getDiscount().doubleValue() ;
            }
            return false;
        });
    }

    static boolean isTriggered(BigDecimal currentProductPrice, Alert alert) {
        return ALERT_TYPES.stream().anyMatch(priceAlert -> priceAlert.isTriggered(currentProductPrice, alert));
    }

    @FunctionalInterface
    private interface PriceAlert {
        boolean isTriggered(BigDecimal currentProductPrice, Alert alert);
    }
}