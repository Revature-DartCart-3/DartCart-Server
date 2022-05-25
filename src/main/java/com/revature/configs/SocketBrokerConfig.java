package com.revature.configs;

import org.springframework.context.ApplicationListener;
//<<<<<<< HEAD
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//
//@SuppressWarnings("deprecation")
//public class SocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker (MessageBrokerRegistry config) {
//        config.enableStompBrokerRelay("/techchat/queue/specific-user");
//        config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
//        config.setUserDestinationPrefix("/techchat/user");
//=======
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Configuration
@EnableWebSocketMessageBroker
public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker (MessageBrokerRegistry registry) {
//        config.enableStompBrokerRelay("/techchat/queue/specific-user");
//        config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
//        config.setUserDestinationPrefix("/techchat/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/chatroom","/user");
        registry.setUserDestinationPrefix("/user");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("/ws").withSockJS();
    	registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
    
    @Component
    public class STOMPDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {

        @Override
        public void onApplicationEvent(SessionDisconnectEvent event) {
        	System.out.println("****Running Custom Disconnect Code****");
            //event.getSessionId();
        	System.out.println(event.getSessionId());
            // event.getUser();
        }
    }

}

