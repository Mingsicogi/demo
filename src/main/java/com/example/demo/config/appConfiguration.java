package com.example.demo.config;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.config.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Configuration
public class appConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem actorSystem = ActorSystem.create("demo-akka-actor-system");
        SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);

        return actorSystem;
    }
}
