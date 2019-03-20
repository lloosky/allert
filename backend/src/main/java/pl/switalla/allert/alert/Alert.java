package pl.switalla.allert.alert;

import lombok.*;
import pl.switalla.allert.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Alert {

    @Id
    private UUID id;

    @Column
    private BigDecimal discount;

    @Column
    @Enumerated(value = EnumType.STRING)
    private DiscountType discountType;

    @ManyToOne
    private Product product;

    @PrePersist
    public void onCreate() {
        this.id = UUID.randomUUID();
    }

    @Builder(builderMethodName = "builder")
    public static Alert newAlert(UUID id, BigDecimal discount, DiscountType discountType, Product product) {
        return new Alert(id, discount, discountType, product);
    }

}
