package pl.switalla.pricealert.shop;

public class Iperfumy implements Shop {

    @Override
    public String getSelector() {
        return ".styled__Wrapper-mirfw-1";
    }

    @Override
    public String getName() {
        return "iperfumy";
    }

    @Override
    public String getDomain() {
        return "iperfumy.pl";
    }
}
