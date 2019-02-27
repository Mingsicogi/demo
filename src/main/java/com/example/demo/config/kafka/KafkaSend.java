package com.example.demo.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class KafkaSend {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String msg){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // kafka에 메세지 전송 실패시, 바로 메일로 보낸다.
                log.error(msg);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                // kafka에 메세지 전송 성공시 info log만 남긴다.
                log.info("Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}
