package com.chens.coblog.api;

import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.domain.Log;
import com.chens.coblog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志管理
 * @author chens
 * @date 20120/8/12
 */
@RestController
@RequestMapping("api/log")
public class ApiLogController {

    @Autowired
    private LogService logService;

    /**
     * 获得最近一周访客数和总访客数
     * @return
     */
    @GetMapping("visitsCount")
    public BaseResponse visitsCount(){
        Map<String,Integer> data=new HashMap<>();
        int totalVisits = logService.getTotalCount();
        data.put("totalVisits",totalVisits);
        int latestVisits=logService.getOneWeekCount();
        data.put("latestVisits",latestVisits);
        return BaseResponse.success("获取成功",data);
    }

    /**
     * 获得最新的n条最新日志
     */
    @GetMapping("latest")
    public BaseResponse latest(int number){
        List<Log> logList=logService.findLatestLog(number);
        return BaseResponse.success("获取成功",logList);
    }

    /**
     * 统计访客的浏览器数据
     */
    @GetMapping("browser")
    public BaseResponse browser(){
        return BaseResponse.success("获取成功",logService.statBrowser());
    }

    /**
     * 统计访客的操作系统
     */
    @GetMapping("operatingSystem")
    public BaseResponse operatingSystem(){
        return BaseResponse.success("获取成功",logService.statOperatingSystem());
    }

    /**
     * 统计访客的所在城市
     */
    @GetMapping("city")
    public BaseResponse city(){
        return BaseResponse.success("获取成功",logService.statCity());
    }
}
