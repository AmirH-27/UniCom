package com.controller;

import com.service.ChannelService;
import com.service.CourseService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CourseController {
//    private CourseService courseService;
//
//    public CourseController (CourseService courseService) {
//        this.courseService = courseService;
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//    }
}
