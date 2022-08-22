package com.controller;

import com.model.ChannelMember;
import com.model.MemberChannel;
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
        System.out.println(user.getMemberChannels().size());
        for(MemberChannel memberChannel : user.getMemberChannels()) {
            System.out.println(memberChannel.getChannel().getCourse().getCourseName());
            System.out.println(memberChannel.getChannel().getSemester().isCurrent());
            for(ChannelMember channelMember : memberChannel.getChannel().getChannelMembers()) {
                System.out.println(channelMember.getUserDetails().getUserName());
            }
        }
        return "home";
    }
}
