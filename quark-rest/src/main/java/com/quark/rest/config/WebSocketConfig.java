package com.quark.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @Author LHR
 *
 * 配置webSocket服务，使用stomp协议
 * Create By 2017/9/6
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    /**
     * 配置了一个消息代理，
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //可以在topic，user域向客户端发送消息
        registry.enableSimpleBroker("/topic","/user");
        //指定用户发送（一对一）的主题前缀是“/user/”
        registry.setUserDestinationPrefix("/user/");
        //客户端向服务端发送时的主题上面需要加"/app"作为前缀；
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 将/quarkServer注册为STOMP端点，这个路径与发送和接收消息的目的路径不同，
     * 这是一个断电，客户端在订阅或发布消息到目的地之前，要连接该端点。在运行中，
     * 用户发送请求url："/applicationName/quarkServer"与STOMP连接。之后再
     * 转发到订阅url；
     *
     * 端点作用: 客户端在订阅或发布消息到目的地址前，需要连接该端点
     * @param stompEndpointRegistry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/quarkServer").setAllowedOrigins("*").withSockJS();//stomp节点
    }
}
