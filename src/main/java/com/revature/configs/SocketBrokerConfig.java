package com.revature.configs;

<<<<<<< HEAD
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@SuppressWarnings("deprecation")
public class SocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker (MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/techchat/queue/specific-user");
        config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
        config.setUserDestinationPrefix("/techchat/user");
=======
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


//@SuppressWarnings("deprecation")

//public class SocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {
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
>>>>>>> 784f8d60b2313e1c29959dd9357494c4ab520708
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
<<<<<<< HEAD
        registry.addEndpoint("techchat/room").withSockJS();
    }

}
=======
        //registry.addEndpoint("/ws").withSockJS();
    	registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

}

>>>>>>> 784f8d60b2313e1c29959dd9357494c4ab520708
