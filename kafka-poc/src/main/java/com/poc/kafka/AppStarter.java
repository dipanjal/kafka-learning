package com.poc.kafka;

import com.poc.kafka.porps.AutoLogProperty;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppStarter implements ApplicationListener<ContextRefreshedEvent> {

    private final AutoLogProperty autoLog;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Seed Enabled: {}", autoLog.isEnabled());
//        MDC.put("appName", appName);

        if(autoLog.isEnabled())
            this.generateLogs();
    }

    private void generateLogs() {
        try{
            for(int i=0; i< autoLog.getCount(); i++) {
                log.info("Generated Test Log {}", i);
                Thread.sleep(autoLog.getDelay());
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }
}
