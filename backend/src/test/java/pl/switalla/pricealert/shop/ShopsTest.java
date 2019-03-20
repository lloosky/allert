package pl.switalla.pricealert.shop;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopsTest {

    @Test
    public void shouldMatchHttpsUrl() {
        String shopUrl = "https://www.morele.net/sluchawki-hyperx-cloud-alpha-hx-hsca-rd-em-979573/";
        Shop shop = Shops.findByUrl(shopUrl).get();
        assertThat(shop.getName()).isEqualTo(new Morele().getName());
    }

    @Test
    public void shouldMatchHttpUrl() {
        String shopUrl = "http://www.morele.net/sluchawki-hyperx-cloud-alpha-hx-hsca-rd-em-979573/";
        Shop shop = Shops.findByUrl(shopUrl).get();
        assertThat(shop.getName()).isEqualTo(new Morele().getName());
    }
}