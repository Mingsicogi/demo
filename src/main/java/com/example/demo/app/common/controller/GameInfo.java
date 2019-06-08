package com.example.demo.app.common.controller;

import com.example.demo.app.common.dto.RestfulDTO;
import com.example.demo.app.common.vo.GameVO;
import com.example.demo.app.common.vo.GameVO2;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@Slf4j
@RequestMapping("/game/info")
public class GameInfo {

    @Autowired
    private GameService gameService;

    @RequestMapping("/list")
    @ResponseBody
    public RestfulDTO.ResParam getGameList(){

        try{
            List<String> result = gameService.getGameList();

            return new RestfulDTO.ResParam(true, "SUCCESS", result);
        }catch (Exception e){
            return new RestfulDTO.ResParam(false, "ERROR", "");
        }
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public RestfulDTO.ResParam getOne(String gameCd){
        try{

            GameVO result = gameService.getGameInfo(GameVO.builder().gameCd(gameCd).build());

            return new RestfulDTO.ResParam(true, "SUCCESS", result);
        }catch (Exception e){
            return new RestfulDTO.ResParam(false, "ERROR", "");
        }
    }

    @RequestMapping("/getOneTest")
    @ResponseBody
    public RestfulDTO.ResParam getOneTest(String gameCd){
        try{

            GameVO result = gameService.getGameInfoTest(new GameVO2(gameCd));

            return new RestfulDTO.ResParam(true, "SUCCESS", result);
        }catch (Exception e){
            return new RestfulDTO.ResParam(false, "ERROR", "");
        }
    }
}

@Service
@Slf4j
class GameService{

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final GameVO LOL = GameVO.builder().gameCd("LOL").gameNm("League of Legend").userCnt("100_000_000").version("1.2.0").startDate("2012-01-01").build();
    private final GameVO SC = GameVO.builder().gameCd("SC").gameNm("StarCraft").userCnt("900_000_000").version("1.9.0").startDate("2017-01-01").build();
    private final GameVO WOW = GameVO.builder().gameCd("WOW").gameNm("World of War").userCnt("400_000_000").version("7.2.0").startDate("2016-01-01").build();
    private final GameVO W3 = GameVO.builder().gameCd("W3").gameNm("WarCraft3").userCnt("250_000_000").version("2.2.2").startDate("2015-01-01").build();
    private final GameVO LG = GameVO.builder().gameCd("LG").gameNm("Linage").userCnt("70_000_000").version("7.2.0").startDate("2014-01-01").build();
    private final GameVO FF = GameVO.builder().gameCd("FF").gameNm("FIFA3").userCnt("10_000_000").version("2.2.1").startDate("2013-01-01").build();
    private final GameVO[] gameList = {LOL, SC, WOW, W3, LG, FF};



    @Cacheable(key = "getGameList")
    public List<String> getGameList() throws Exception{
        Thread.sleep(4000); // db processing time


        return Arrays.asList(gameList).stream().map(gameVO -> gameVO.getGameNm()).collect(toList());
    }

    @Cacheable(key = "getGameInfo")
    public GameVO getGameInfo(GameVO param) throws Exception{
        log.info("####### 상속받지 않은 VO를 사용한 메소드에 캐쉬를 사용했습니다.");
        Thread.sleep(2000);

        return Arrays.asList(gameList).stream().filter(gameVO -> StringUtils.equals(param.getGameCd(), gameVO.getGameCd())).collect(toList()).get(0);
    }

    @Cacheable(key = "getGameInfoExtendsVO")
    public GameVO getGameInfoTest(GameVO2 param) throws Exception{
        log.info("####### 상속받은 VO를 사용한 메소드에 캐쉬를 사용했습니다.");
        Thread.sleep(2000);

        return Arrays.asList(gameList).stream().filter(gameVO -> StringUtils.equals(param.getGameCd(), gameVO.getGameCd())).collect(toList()).get(0);
    }
}
