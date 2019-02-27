package com.example.demo.config;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.config.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * singleton으로 사용하기 위한 객체 설정
 *
 * @author 전민석
 */
@Configuration
@ComponentScan("com.example.demo.app.common.actor.*")
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Akka 사용을 위해 ActorSystem을 Bean으로 등록
     *
     * @return
     */
    @Bean
    public ActorSystem actorSystem(){
        ActorSystem actorSystem = ActorSystem.create("demo-akka-actor-system");
        SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);

        return actorSystem;
    }
}
