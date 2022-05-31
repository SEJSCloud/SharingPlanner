package com.sharingplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String test() {
        return "/login";
    }

    @RequestMapping("/index")
    public String test1() {
        return "/index";
    }
}
