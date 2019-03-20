package pl.switalla.allert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.switalla.allert.alert.AlertProcessor;
import pl.switalla.allert.alert.AlertRestRepository;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AlertRestRepository alertRepository;

    @Autowired
    private AlertProcessor alertProcessor;

//    @Scheduled(fixedRate = 30 * 60 * 1000)
    @Scheduled(fixedRate = 10000)
    void scheduler() {
        alertRepository.findAll().forEach(alert -> {
            alertProcessor.process(alert);
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
