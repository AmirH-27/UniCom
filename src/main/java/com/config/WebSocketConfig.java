package com.config;



////@Configuration
////@EnableWebSocketMessageBroker
////@EnableWebSocket
////@EnableWebMvc
//public class WebSocketConfig {
//
////    @Override
////    public void configureMessageBroker(MessageBrokerRegistry registry) {
////        registry.enableSimpleBroker("/topic");
////        registry.setApplicationDestinationPrefixes("/app");
////    }
////
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
//////        RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
//////        registry.addEndpoint("/ws")
//////                .withSockJS();
//////
//////        registry.addEndpoint("/ws")
//////                .setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
//////                .setAllowedOrigins("*");
////
////    }
////    @Override
////    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////        registry.addHandler(new MyHandler(), "/myHandler")
////            .setAllowedOrigins("*");
////    }
//}

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}
