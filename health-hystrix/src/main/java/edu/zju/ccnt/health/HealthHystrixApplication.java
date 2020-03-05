package edu.zju.ccnt.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class HealthHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthHystrixApplication.class, args);
    }

}
