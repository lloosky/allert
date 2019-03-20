package pl.switalla.allert.alert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class AlertDispatcher {

    void dispatch(Alert alert) {
        log.info("Dispatching: {}", alert);
    }
}
