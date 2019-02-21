package com.example.demo.app.common.actor;

import akka.actor.UntypedAbstractActor;
import com.example.demo.config.akka.Actor;
import org.springframework.stereotype.Component;

@Component("PrintActor")
@Actor
public class PrintActor extends UntypedAbstractActor {
	@Override public void onReceive(Object message) throws Throwable {
		if(message instanceof String){
				System.out.println(message);
		} else {
			unhandled(message);
		}
	}
}
