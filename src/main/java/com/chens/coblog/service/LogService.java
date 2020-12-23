package com.chens.coblog.service;

import com.chens.coblog.domain.Log;

import java.util.List;
import java.util.Map;

/**
 * 日志接口
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface LogService {

    int getTotalCount();

    int getOneWeekCount();

    void error(String data, String message);

    /**
     * 获取最新的n条日志
     * @param number 需要获取的条数
     * @return
     */
    List<Log> findLatestLog(int number);

    /**
     * 统计访客的浏览器类型
     */
    Map<String,Integer> statBrowser();

    /**
     * 统计访客的操作系统
     */
    Map<String,Integer> statOperatingSystem();

    /**
     * 统计访客的所在城市
     */
    List<Map<String,Integer>> statCity();

    /**
     * 添加日志
     */
    void addLog(Log log);


}




