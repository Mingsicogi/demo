package com.example.demo.app.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class RestfulDTO {

    @Data
    @AllArgsConstructor
    public static class ReqParam{
        private Object data;
    }

    @Data
    @AllArgsConstructor
    public static class ResParam{
        private boolean success;
        private String resCode;
        private Object data;
    }
}
