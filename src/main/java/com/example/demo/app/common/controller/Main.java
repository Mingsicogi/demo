package com.example.demo.app.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {

    private String chanelId = "1584207381";
    private String callBackUrl = "http://1.240.34.111:18080/main";

    @RequestMapping(value = {"", "/", "/main"})
    public String main(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name, Model model) {

        model.addAttribute("name", name);

        return "main";
    }

    @GetMapping("/login")
    public String login(){
        String oAuthUrl = "https://access.line.me/dialog/oauth/weblogin?response_type=code&client_id=%s&redirect_uri=%s&state=123";

        oAuthUrl = String.format(oAuthUrl, chanelId, callBackUrl);

        return "redirect:" + oAuthUrl;
    }
}
