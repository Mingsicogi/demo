package com.example.demo.app.common.controller;

import com.example.demo.app.common.actor.ActorManagement;
import com.example.demo.app.common.vo.HealthCheckVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/healthCheck")
@Slf4j
public class HealthCheck {

    @Autowired
    private ActorManagement actorManagement;

    final private String password = "mingsicogi";

    @RequestMapping("/basic")
    @ResponseBody
    public HealthCheckVO basicHealthCheck(HttpServletRequest request){
        if(request != null){
            return new HealthCheckVO("true", "Stable", "OK");
        }

        return new HealthCheckVO("false", "UnStable", "Fail");
    }

    @RequestMapping("/monitoringPage/{pw}")
    public String getMtrPage(@PathVariable("pw") String pw, Model model){
        String isOK = "false";
        String status = "UnStable";
        String message = "Fail";

        if(!password.equals(pw)){
            message = "Password Fail";
        } else {
            isOK = "true";
            status = "Stable";
            message = "OK";
        }

        HealthCheckVO vo = new HealthCheckVO(isOK, status, message);
        model.addAttribute("result", vo);

        return "mtrPage";
    }

    @RequestMapping("/akka")
    @ResponseBody
    public String startAkkaService(String message){
        actorManagement.start(message);

        return "ok";
    }
}


