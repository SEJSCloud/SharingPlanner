package com.sharingplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/sign-up")
    public String signUp() {
        return "/signUp";
    }
}
