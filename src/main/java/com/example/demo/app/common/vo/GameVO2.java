package com.example.demo.app.common.vo;

import lombok.Data;

@Data
public class GameVO2 extends GameCommonVO {
    private String version;
    private String userCnt;
    private String startDate;

    public GameVO2(String gameCd){
        super(gameCd);
    }

}
