package com.example.demo.common.log;

import com.example.demo.config.kafka.KafkaSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @Autowired
    private KafkaSend kafkaSend;

    @ExceptionHandler(value = {Exception.class})
    protected String globalExceptionHandling(Exception e){
        kafkaSend.send("mytopic", e.getMessage());
        log.info("Sent error message to kafka... ...");
        return "errorPage";
    }
}
