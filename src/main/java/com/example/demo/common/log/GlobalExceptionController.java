package com.example.demo.common.log;

import com.example.demo.config.kafka.KafkaSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @Autowired
    private KafkaSend kafkaSend;

    @Value("${kafka.use}")
    private boolean useKafka;

    @ExceptionHandler(value = {Exception.class})
    protected String globalExceptionHandling(Exception e){
        if(useKafka){
            kafkaSend.send("mytopic", e.getMessage());
            log.info("Sent error message to kafka... ...");
        } else {
            log.error("#### kafka is not activate... please check error : {}", e.getMessage());
        }

        return "errorPage";
    }
}
