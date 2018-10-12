package edu.free.magpie.service;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaServer
@SpringCloudApplication
public class ServiceEureka2Stater {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ServiceEureka2Stater.class).profiles("slave2").run(args);
    }

}
