package pl.switalla.pricealert.product;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    private UUID id;

    @Column
    @Setter
    private BigDecimal price;

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private String shop;

    @PrePersist
    public void onCreate() {
        this.id = UUID.randomUUID();
    }

    @Builder(builderMethodName = "builder")
    public static Product newProduct(UUID id, BigDecimal price, String name, String url, String shop) {
        return new Product(id, price, name, url, shop);
    }
}
