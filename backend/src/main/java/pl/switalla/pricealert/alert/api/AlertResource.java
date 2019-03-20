package pl.switalla.pricealert.alert.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.switalla.pricealert.alert.Alert;
import pl.switalla.pricealert.alert.AlertRestRepository;
import pl.switalla.pricealert.alert.DiscountType;
import pl.switalla.pricealert.product.Product;
import pl.switalla.pricealert.product.ProductRestRepository;
import pl.switalla.pricealert.shop.Shop;
import pl.switalla.pricealert.shop.Shops;

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
