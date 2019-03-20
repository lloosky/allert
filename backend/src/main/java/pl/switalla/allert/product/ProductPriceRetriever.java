package pl.switalla.allert.product;

import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.switalla.allert.exception.PriceAlertException;
import pl.switalla.allert.shop.Shop;
import pl.switalla.allert.shop.Shops;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
public class ProductPriceRetriever {

    @Autowired
    private ProductRestRepository productRestRepository;

    public BigDecimal retrieve(Product product) {
        Map<String, Shop> shops = Shops.all();
        if (!shops.containsKey(product.getShop())) {
            throw new PriceAlertException(String.format("Shop %s does not exist.", product.getShop()));
        }

        Shop shop = shops.get(product.getShop());
        try {
            Document document = Jsoup.connect(product.getUrl()).get();
            Elements priceElement = document.select(shop.getSelector());
            if (priceElement.size() > 1) {
                throw new PriceAlertException("Multiple prices elements.");
            }

            log.debug("Price retriever: |{}|", priceElement.text());
            Money price = PriceParser.parse(priceElement.text());
            if (product.getPrice() == null) {
                product.setPrice(price.getNumberStripped());
                productRestRepository.save(product);
            }

            log.info("Product: {}, price: {}, newPrice: {}, shop: {}",
                    product.getName(),
                    product.getPrice().doubleValue(),
                    price.getNumberStripped().doubleValue(),
                    product.getShop()
            );

            return price.getNumberStripped();
        } catch (IOException e) {
            log.error("Fetching failed.", e);
            throw new PriceAlertException("Unable to fetch product price.");
        }
    }
}
