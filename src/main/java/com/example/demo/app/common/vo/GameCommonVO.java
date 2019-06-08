package com.example.demo.app.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
public class GameCommonVO {
    private String gameCd;
    private String gameNm;

    public GameCommonVO(String gameCd){
        this.gameCd = gameCd;
    }
}
