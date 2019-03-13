package edu.zju.ccnt.healthservicecategory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("edu.zju.ccnt.healthservicecategory.dao")
public class HealthServicecategoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthServicecategoryApplication.class, args);
    }

}
