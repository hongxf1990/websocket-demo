package com.petter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author hongxf
 * @since 2017-07-05 9:35
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 添加一个服务端点，来接收客户端的连接。
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //表示添加了一个/chat端点，客户端就可以通过这个端点来进行连接。
        registry.addEndpoint("/chat").withSockJS();  //withSockJS()的作用是开启SockJS支持，
    }

    /**
     * 定义消息代理，通俗一点讲就是设置消息连接请求的各种规范信息。
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
        registry.enableSimpleBroker("/topic", "/user");

        //服务端接收地址的前缀，意思就是说客户端向服务端发消息的地址的前缀
        registry.setApplicationDestinationPrefixes("/app");

        //一对一指定用户发送消息，添加的前缀
        registry.setUserDestinationPrefix("/user/");
    }
}
