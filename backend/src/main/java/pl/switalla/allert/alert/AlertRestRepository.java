package pl.switalla.allert.alert;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AlertRestRepository extends CrudRepository<Alert, UUID> {
}
