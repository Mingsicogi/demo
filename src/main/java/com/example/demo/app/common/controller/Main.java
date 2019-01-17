package com.example.demo.app.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {

    @RequestMapping(value = {"", "/", "/main"})
    public String main(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name, Model model) {

        model.addAttribute("name", name);

        return "main";
    }
}
