package pl.switalla.allert.alert.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.switalla.allert.alert.Alert;
import pl.switalla.allert.alert.AlertRestRepository;
import pl.switalla.allert.alert.DiscountType;
import pl.switalla.allert.product.Product;
import pl.switalla.allert.product.ProductRestRepository;
import pl.switalla.allert.shop.Shop;
import pl.switalla.allert.shop.Shops;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class AlertResource {

    @Autowired
    private AlertRestRepository alertRestRepository;

    @Autowired
    private ProductRestRepository productRestRepository;

    @PostMapping(path = "/api/alerts")
    public ResponseEntity<?> createAlert(@RequestBody CreateAlertBody createAlertBody) {
        Optional<Shop> shop = Shops.findByUrl(createAlertBody.getProductUrl());
        if (!shop.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = Product.builder()
                .name("test product")
                .url(createAlertBody.getProductUrl())
                .shop(shop.get().getName())
                .build();
        productRestRepository.save(product);

        Alert alert = Alert.builder()
                .discount(BigDecimal.valueOf(createAlertBody.getDiscount()))
                .discountType(DiscountType.valueOf(createAlertBody.getDiscountType()))
                .product(product)
                .build();
        alertRestRepository.save(alert);

        return new ResponseEntity<>(alert, HttpStatus.CREATED);
    }
}
