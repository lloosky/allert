package pl.switalla.allert.alert.api;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class CreateAlertBody {
    private String discountType;
    private Double discount;
    private String productUrl;
    private String email;
}
