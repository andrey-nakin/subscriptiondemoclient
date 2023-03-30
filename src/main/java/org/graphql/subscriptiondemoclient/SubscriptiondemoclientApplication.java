package org.graphql.subscriptiondemoclient;

import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.network.ws.GraphQLWsProtocol;
import com.apollographql.apollo3.rx3.Rx3Apollo;

import io.reactivex.rxjava3.core.Flowable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graphql.subscriptiondemoclient.properties.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SubscriptiondemoclientApplication implements CommandLineRunner {

    private final AppProperties properties;

    @Override
    public void run(final String... args) {
        log.info("Started");
        log.info("Target host: {}", properties.getHost());
        log.info("Event payload: {}", properties.getPayload());
        log.info("Event limit: {}", properties.getLimit());

        try (var apolloClient = new ApolloClient.Builder().serverUrl(properties.getHost())
                .webSocketServerUrl(properties.getHost())
                .wsProtocol(new GraphQLWsProtocol.Factory())
                .build()) {

            final Flowable<ApolloResponse<EventsSubscription.Data>> subscription = Rx3Apollo.flowable(apolloClient.subscription(
                    new EventsSubscription(properties.getInterval(), properties.getPayload(), properties.getLimit())));

            subscription.blockingForEach(data -> {
                if (data.data != null) {
                    log.info("Event: id={}, payload={}", data.data.event.id, data.data.event.payload);
                } else {
                    log.info("Event: error={}", data.errors);
                }
            });
        }

        log.info("Finished");
    }

    public static void main(String[] args) {
        SpringApplication.run(SubscriptiondemoclientApplication.class, args);
    }
}
