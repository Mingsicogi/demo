package com.example.demo.app.common.controller;

import com.example.demo.app.common.actor.ActorManagement;
import com.example.demo.app.common.vo.HealthCheckVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/healthCheck")
@Slf4j
public class HealthCheck {

    private static int count = 0;

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
        //checkCount();
        //actorManagement.start(message);

        return "ok";
    }

    @RequestMapping("/error")
    @ResponseBody
    public String occurError(String message){
        int a = 1/0;

        return "error";
    }

    /**
     * ehcache의 키 정보들을 조회
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ehCache/keys", method = RequestMethod.POST)
    public ArrayList<EhCacheKeysVO> ehCacheKeys() {

        ArrayList<EhCacheKeysVO> rtnList = new ArrayList<>();
        String[] cacheNames = EhCacheManagerUtils.buildCacheManager().getCacheNames();
        for (String cacheNm : cacheNames) {

            List<?> keys = EhCacheManagerUtils.buildCacheManager().getCache(cacheNm).getKeys();

            ArrayList<String> keyList = new ArrayList<>();
            for (Object key : keys) {
                keyList.add(key.toString());
            }

            rtnList.add(new EhCacheKeysVO(cacheNm, keyList));
        }

        log.info("ehCache key info: {}", rtnList);

        return rtnList;
    }

    synchronized private void checkCount(){
        log.info("" + count++);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class EhCacheKeysVO {

    private String cacheNm;
    private List<String> keys;

}

