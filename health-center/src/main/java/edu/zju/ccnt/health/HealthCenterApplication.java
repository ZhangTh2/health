package edu.zju.ccnt.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HealthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCenterApplication.class, args);
    }

}
