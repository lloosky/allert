package pl.switalla.pricealert.product;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;

import javax.money.format.AmountFormatQuery;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

@UtilityClass
@Slf4j
class PriceParser {

    private static final Locale LOCALE = Locale.forLanguageTag("pl-PL");

    static Money parse(String priceText) {
        AmountFormatQuery formatQuery = AmountFormatQuery.of(LOCALE);
        String text = clearPriceText(priceText);
        return Money.parse(text, MonetaryFormats.getAmountFormat(formatQuery));
    }

    private static String clearPriceText(String priceText) {
        return priceText
                .replaceAll(" ", "")
                .replaceAll("zł", "")
                .replaceAll("ZŁ", "")
                .trim()
                .concat(" PLN");
    }
}
