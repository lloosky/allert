package pl.switalla.pricealert.shop;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Shops {

    private static final Map<String, Shop> SHOPS = new HashMap<>();

    static {
        SHOPS.put("xkom", new Xkom());
        SHOPS.put("morele", new Morele());
        SHOPS.put("iperfumy", new Iperfumy());
    }

    public static Map<String, Shop> all() {
        return Collections.unmodifiableMap(SHOPS);
    }

    public static Optional<Shop> findByUrl(String shopUrl) {
        return SHOPS.values().stream()
                .filter(shop -> shopUrl.matches("http[s]?://.*"+ shop.getDomain() +".*"))
                .findFirst();
    }
}
