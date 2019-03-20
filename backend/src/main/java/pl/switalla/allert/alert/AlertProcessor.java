package pl.switalla.allert.alert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.switalla.allert.product.ProductPriceRetriever;

import java.math.BigDecimal;

@Slf4j
@Service
public class AlertProcessor {

    @Autowired
    private ProductPriceRetriever priceRetriever;

    @Autowired
    private AlertDispatcher alertDispatcher;

    public void process(Alert alert) {
        BigDecimal price = priceRetriever.retrieve(alert.getProduct());
        if (AlertTypes.isTriggered(price, alert)) {
            alertDispatcher.dispatch(alert);
        }
    }
}
