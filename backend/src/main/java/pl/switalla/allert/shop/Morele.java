package pl.switalla.allert.shop;

public class Morele implements Shop {

    @Override
    public String getSelector() {
        return "#product_price_brutto.price-new";
    }

    @Override
    public String getName() {
        return "morele";
    }

    @Override
    public String getDomain() {
        return "morele.net";
    }
}
