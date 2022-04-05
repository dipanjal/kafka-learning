package com.poc.kafka.porps;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mysql-to-es.auto-log")
@Data
public class AutoLogProperty {
    private boolean enabled;
    private int count;
    private long delay;
}
