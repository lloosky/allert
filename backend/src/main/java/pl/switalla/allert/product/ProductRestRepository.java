package pl.switalla.allert.product;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRestRepository extends CrudRepository<Product, UUID> {
}
