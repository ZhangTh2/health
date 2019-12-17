package edu.zju.ccnt.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class HealthConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthConfigApplication.class, args);
    }

}
