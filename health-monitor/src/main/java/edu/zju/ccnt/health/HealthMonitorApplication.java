package edu.zju.ccnt.health;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class HealthMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthMonitorApplication.class, args);
    }

}
