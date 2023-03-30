package org.graphql.subscriptiondemoclient.properties;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Getter
@Setter
public class AppProperties {

    @NonNull
    private String host;

    @NonNull
    private Integer interval;

    @NonNull
    private String payload;

    @NonNull
    private Integer limit;
}
