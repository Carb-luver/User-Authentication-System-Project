package com.RemoteMode.internship2correction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticController {

    @RequestMapping("/")
    public String getLoginPage(){
        return "start";
    }

    @RequestMapping("/main")
    public String getMainPage(){
        return "main";
    }

}
