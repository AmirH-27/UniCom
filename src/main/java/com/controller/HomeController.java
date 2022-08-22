package com.controller;

import com.model.Channel;
import com.model.ChannelMember;
import com.model.User;
import com.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    UserService userService;
    public HomeController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String index(Model model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userName", authentication.getName());
        int id = userService.findUserIDByStudentId(authentication.getName());
        System.out.println(id);
        User user = userService.get(id);
        System.out.println(user.getUserName());
        System.out.println(user.getChannelMembers().size());
        for(ChannelMember channelMember : user.getChannelMembers()) {
            System.out.println(channelMember.getChannel().getCourse().getCourseName());
            System.out.println(channelMember.getChannel().getSemester().isCurrent());
        }
        return "home";
    }
}
