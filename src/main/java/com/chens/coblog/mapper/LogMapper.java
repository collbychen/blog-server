package com.chens.coblog.mapper;

import com.chens.coblog.common.utils.TkMapper;
import com.chens.coblog.domain.Log;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 日志Mapper
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
public interface LogMapper extends TkMapper<Log> {

    /**
     * 查询最新的日志
     * @param number 数量
     * @return list 日志数组
     */
    List<Log> selectLatestLog(int number);

    /**
     * 查询所有UserAgent
     * @return list 日志数组
     */
    @Select("select user_agent from log")
    List<String> selectAllUserAgent();

    /**
     * 统计访问最多的十个城市
     * @return list 数组
     */
    @Select("SELECT city,COUNT(city) count FROM log GROUP BY city ORDER BY count DESC LIMIT 10")
    List<Map<String,Integer>> selectCityCount();

    /**
     * 查询所有浏览器
     * @return list 数组
     */
    @Select("select browser from log")
    List<String> selectAllBrowser();

    /**
     * 查询所有操作系统
     * @return list 数组
     */
    @Select("select operating_system from log")
    List<String> selectAllOperatingSystem();

    /**
     * 查找一周日志的数量
     * @return int 数量
     */
    @Select("SELECT count(*) FROM `log` where DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(create_time);")
    Integer getTotalCount();
}