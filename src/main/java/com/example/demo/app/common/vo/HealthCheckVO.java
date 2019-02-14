package com.example.demo.app.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class HealthCheckVO {
    private String isOK;
    private String status;
    private String message;
}
