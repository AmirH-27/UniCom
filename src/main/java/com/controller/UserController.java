package com.controller;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("isCheck",true);
        model.addAttribute("isRegister",false);
        model.addAttribute("isWrongAuth",false);
        model.addAttribute("isLogin",false);
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("view","CHECK");
        return "login";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors() && bindingResult.getFieldErrorCount() > 4) {
            return "login";
        }
        System.out.println(user.getStudentID());
        if(userService.checkUser(user.getStudentID())){
            model.addAttribute("isRegister",false);
            model.addAttribute("isLogin",true);
        }else{
            model.addAttribute("isRegister",true);
            model.addAttribute("isLogin",false);
        }
        model.addAttribute("isWrongAuth",false);
        model.addAttribute("isCheck",false);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors() && bindingResult.getFieldErrorCount() > 3) {
            return "login";
        }
        AuthenticationManager authManager = (AuthenticationManager) req.getServletContext().getAttribute("org.springframework.security.authenticationManager");
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getStudentID(), user.getUserPass());
        Authentication auth = authManager.authenticate(authReq);
        if(auth!=null){
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = req.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            return "home";
        }
        model.addAttribute("isCheck",false);
        model.addAttribute("isRegister",false);
        model.addAttribute("isWrongAuth",true);
        model.addAttribute("isLogin",true);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        return "home";
    }
}
