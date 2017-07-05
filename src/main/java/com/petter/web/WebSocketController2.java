package com.petter.web;

import com.petter.bean.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 一对一聊天示例
 * @author hongxf
 * @since 2017-05-09 16:46
 */
@Controller
public class WebSocketController2 {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 进入示例页面
     * @return
     */
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String index() {
        return "chat";
    }


    /**
     * 客户端发送信息到 /chat-one-to-one
     * 后台发送给指定用户，并且该用户监听地址为 /user/topic/one-to-one
     * @param chatMessage
     */
    @MessageMapping("/chat-one-to-one")
    public void handleChat(
            ChatMessage chatMessage
    ) {
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getUsername(), "/topic/one-to-one", chatMessage.getContent());
    }
}
