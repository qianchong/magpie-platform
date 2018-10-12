package edu.free.magpie.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceUserStarter {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserStarter.class, args);
    }
}
