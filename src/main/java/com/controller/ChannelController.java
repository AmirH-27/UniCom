package com.controller;

import com.model.*;
import com.service.ChannelService;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ChannelController {
    MessageService messageService;
    ChannelService channelService;
    public ChannelController(MessageService messageService, ChannelService channelService) {
        this.messageService = messageService;
        this.channelService = channelService;
    }

    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.GET)
    public String channel(@PathVariable("channelId") int channelId, Model model, HttpServletRequest request) {
        User user = HomeController.user;
        Channel channel = new Channel();
        for(MemberChannel memberChannel : user.getMemberChannels()) {
            if(memberChannel.getChannelId() == channelId) {
                channel = memberChannel.getChannel();
                break;
            }
        }
        List<Message> messages = messageService.getAll(channel.getChannelId());
        model.addAttribute("members", channel.getChannelMembers());
        model.addAttribute("messages", messages);
        String path = request.getServletPath();
        model.addAttribute("path", path);
        return "channel";
    }

    @MessageMapping("/ws")
    @SendTo("/topic/messages")
    public HelloMessage response(String message){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response(message);
    }
}
