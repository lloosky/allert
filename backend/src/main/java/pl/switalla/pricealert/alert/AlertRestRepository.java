package pl.switalla.pricealert.alert;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AlertRestRepository extends CrudRepository<Alert, UUID> {
}
