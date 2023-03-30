package org.graphql.subscriptiondemoclient;

import lombok.extern.slf4j.Slf4j;
import org.graphql.subscriptiondemoclient.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SubscriptiondemoclientApplication {

    public SubscriptiondemoclientApplication(final AppProperties properties) {
        log.info("Target host: {}", properties.getHost());
        log.info("Event payload: {}", properties.getPayload());
        log.info("Event limit: {}", properties.getLimit());
    }

    public static void main(String[] args) {
        SpringApplication.run(SubscriptiondemoclientApplication.class, args);
    }
}
