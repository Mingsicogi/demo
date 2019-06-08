package com.example.demo.app.common.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class GameVO {
    private String gameNm;
    private String gameCd;
    private String version;
    private String userCnt;
    private String startDate;
}
