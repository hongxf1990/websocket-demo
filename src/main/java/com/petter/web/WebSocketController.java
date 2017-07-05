package com.petter.web;

import com.petter.bean.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author hongxf
 * @since 2017-05-09 16:46
 */
@Controller
public class WebSocketController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;


    /**
     * 进入示例页面
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "web-socket";
    }

    /**
     * 进入示例页面
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index2() {
        return "web-socket2";
    }

    /**
     * 客户端发送消息到/app/change-notice
     * 所有监听/topic/notice 地址的客户端会收到该信息
     * @param value
     * @return
     */
    @MessageMapping("/change-notice")
    public void greeting(String value){
        simpMessagingTemplate.convertAndSend("/topic/notice", value);
    }

    /**
     * 客户端发送消息到/app/change-notice2
     * 所有监听/topic/notice 地址的客户端会收到该信息
     * @param value
     * @return
     */
    @MessageMapping("/change-notice2")
    @SendTo("/topic/notice")
    public String greeting2(String value){
        return value;
    }

    /**
     * 客户端发送消息到/app/change-notice3
     * 只用监听/topic/notice/ + id 地址的客户端会收到该信息
     * @return
     */
    @MessageMapping("/change-notice3")
    public void greeting3(
            Message message
    ){
        //修改了message内容
        message.setContent(message.getContent() + "---" + LocalDateTime.now());
        //监听地址发生改变
        simpMessagingTemplate.convertAndSend("/topic/notice/" + message.getId(), message);
    }

}
