package com.example.demo.app.common.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.RoundRobinPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.example.demo.config.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Service
public class ActorManagement {

    @Autowired
    private ActorSystem actorSystem;

    private ActorRef rootActor;

    @PostConstruct
    public void init(){
        rootActor = actorSystem.actorOf(new RoundRobinPool(5).props(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("RootActor")));
    }

    public void start(Object object){
        rootActor.tell(object, ActorRef.noSender());
    }
}
