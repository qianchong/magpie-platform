package edu.free.magpie.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaServer
@SpringBootApplication
public class ServiceEureka1Stater {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ServiceEureka1Stater.class).profiles("slave1").run(args);
    }

}
