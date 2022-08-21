package com.controller;

import com.model.Channel;
import com.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ChannelController {
//    private ChannelService channelService;
//
//    public ChannelController (ChannelService channelService) {
//        this.channelService = channelService;
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }
//    @GetMapping("/home")
//    public String home(Model model) {
//        model.addAttribute("Channel", new Channel());
//        model.addAttribute("channels", channelService.getAllChannels());
//        return "home";
//    }
//    @GetMapping("/channel/{channelId}")
//    public String channel(@PathVariable("id") int id, Model model) {
//        model.addAttribute("Channel", new Channel());
//        model.addAttribute("channels", channelService.get(id));
//        return "viewChannel";
//    }
//    @GetMapping("/channel/{channelId}/members")
//    public String channelMembers(@PathVariable("id") int id, Model model) {
//        model.addAttribute("Channel", new Channel());
//        model.addAttribute("channels", channelService.getChannelMembers(id));
//        return "viewChannelMembers";
//    }
//

}
