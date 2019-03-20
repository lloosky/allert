package pl.switalla.pricealert.product;

import org.javamoney.moneta.Money;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceParserTest {

    @Test
    public void shouldReturnCorrectPrice() {
        Money price = PriceParser.parse("1 000zł");
        assertThat(price.getNumber().intValueExact()).isEqualTo(1000);

        price = PriceParser.parse("3 399 zł");
        assertThat(price.getNumber().intValueExact()).isEqualTo(3399);

        price = PriceParser.parse("1 000,00 zł");
        assertThat(price.getNumber().intValueExact()).isEqualTo(1000);

        price = PriceParser.parse("1 000,10 zł");
        assertThat(price.getNumber().doubleValueExact()).isEqualTo(1000.1);

        price = PriceParser.parse("1000,10 zł");
        assertThat(price.getNumber().doubleValueExact()).isEqualTo(1000.1);

        price = PriceParser.parse("1000,10 ZŁ");
        assertThat(price.getNumber().doubleValueExact()).isEqualTo(1000.1);
    }
}