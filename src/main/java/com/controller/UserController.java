package com.controller;

import com.model.User;
import com.service.CustomAuthenticationProvider;
import com.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if(isLoggedIn()){
            return "redirect:/";
        }
        model.addAttribute("isCheck","nothing");
        model.addAttribute("isRegister","hidden");
        model.addAttribute("isLogin","hidden");
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(isLoggedIn()){
            return "redirect:/";
        }
        if (bindingResult.hasErrors() && bindingResult.getFieldErrorCount() > 2) {
            model.addAttribute("isCheck","nothing");
            model.addAttribute("isRegister","hidden");
            model.addAttribute("isLogin","hidden");
            model.addAttribute("user", user);
            model.addAttribute("studentIDError", bindingResult.hasFieldErrors("studentID") ? bindingResult.getFieldError("studentID").getDefaultMessage() : "");
            return "login";
        }
        if(userService.checkUser(user.getStudentID())){
            model.addAttribute("isRegister","hidden");
            model.addAttribute("isLogin","nothing");
        }else{
            model.addAttribute("isRegister","nothing");
            model.addAttribute("isLogin","hidden");
        }
        model.addAttribute("isCheck","hidden");
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/signing", method = RequestMethod.POST)
    public String login(HttpServletRequest req, Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(isLoggedIn()){
            return "redirect:/";
        }
        boolean rememberMe = req.getParameter("rememberMe") != null;
        if (bindingResult.hasErrors() && bindingResult.getFieldErrorCount() > 1) {
            model.addAttribute("isRegister","hidden");
            model.addAttribute("isLogin","nothing");
            model.addAttribute("isCheck","hidden");
            model.addAttribute("user", user);
            model.addAttribute("studentIDError", bindingResult.hasFieldErrors("studentID") ? bindingResult.getFieldError("studentID").getDefaultMessage() : "");
            model.addAttribute("userPassError", bindingResult.hasFieldErrors("userPass") ? bindingResult.getFieldError("userPass").getDefaultMessage() : "");
            return "login";
        }
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider(userService);
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getStudentID(), user.getUserPass());
        Authentication auth = authenticationProvider.authenticate(authReq);
        if(auth!=null){
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = req.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            if(rememberMe){
                session.setMaxInactiveInterval(60*60*24*7);
            }
            return "redirect:/";
        }
        model.addAttribute("isCheck","hidden");
        model.addAttribute("isRegister","hidden");
        model.addAttribute("isLogin","nothing");
        model.addAttribute("userPassError", "Wrong password");
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest req, Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(isLoggedIn()){
            return "redirect:/";
        }
        String confirmPass = req.getParameter("confirmUserPass");
        String confirm = "";
        boolean confirmPassError = false;
        if(confirmPass.equals("")){
            confirm = "Confirm password is required";
            confirmPassError = true;
        }else if(!confirmPass.equals(user.getUserPass())){
            confirm = "Confirm password is not match";
            confirmPassError = true;
        }
        if (bindingResult.hasErrors() || confirmPassError) {
            model.addAttribute("isRegister","nothing");
            model.addAttribute("isLogin","hidden");
            model.addAttribute("isCheck","hidden");
            model.addAttribute("user", user);
            model.addAttribute("studentIDError", bindingResult.hasFieldErrors("studentID") ? bindingResult.getFieldError("studentID").getDefaultMessage() : "");
            model.addAttribute("userPassError", bindingResult.hasFieldErrors("userPass") ? bindingResult.getFieldError("userPass").getDefaultMessage() : "");
            model.addAttribute("userNameError", bindingResult.hasFieldErrors("userName") ? bindingResult.getFieldError("userName").getDefaultMessage() : "");
            model.addAttribute("confirmUserPassError", confirm);
            return "login";
        }
        user.setUserEmail(user.getStudentID()+"@student.aiub.edu");
        userService.save(user);
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider(userService);
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(user.getStudentID(), user.getUserPass());
        Authentication auth = authenticationProvider.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return "redirect:/";
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    public boolean isLoggedIn(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }
}
