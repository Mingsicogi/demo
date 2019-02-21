package com.example.demo.app.common.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedAbstractActor;
import akka.routing.RoundRobinPool;
import com.example.demo.config.akka.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.example.demo.config.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Component("RootActor")
@Actor
public class RootActor extends UntypedAbstractActor {

	@Autowired
	private ActorSystem actorSystem;

	private ActorRef printActor;

	@PostConstruct
	public void init(){
		printActor = context().actorOf(new RoundRobinPool(10000).props(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("PrintActor")));
	}

	@Override public void onReceive(Object message) throws Throwable {
		if(message instanceof String){
            printActor.tell(message, ActorRef.noSender());
		} else {
			unhandled(message);
		}
	}
}
