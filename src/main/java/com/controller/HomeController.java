package com.controller;

import com.model.ChannelMember;
import com.model.MemberChannel;
import com.model.User;
import com.model.UserCourseArchive;
import com.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    UserService userService;
    public static User user;
    public HomeController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String index(Model model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userName", authentication.getName());
        int id = userService.findUserIDByStudentId(authentication.getName());
        user = userService.get(id);
        List<MemberChannel> memberChannels = new ArrayList<>();
        for(MemberChannel memberChannel : user.getMemberChannels()) {
            if(memberChannel.getChannel().getSemester().isCurrent()) {
                memberChannels.add(memberChannel);
            }
        }
        user.setMemberChannels(memberChannels);
        model.addAttribute("user", user);
        return "home";
    }
}
