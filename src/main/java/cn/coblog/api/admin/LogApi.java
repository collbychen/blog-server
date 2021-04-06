package cn.coblog.api.admin;

import cn.coblog.common.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日志管理
 * @author chens
 * @date 20120/8/12
 */
@RequestMapping("log")
public interface LogApi {

    /**
     * 获得最近一周访客数和总访客数
     */
    @GetMapping("visitsCount")
    BaseResponse visitsCount();

    /**
     * 获得最新的n条最新日志
     */
    @GetMapping("latest")
    BaseResponse latest(int number);

    /**
     * 统计访客的浏览器数据
     */
    @GetMapping("browser")
    BaseResponse browser();
    /**
     * 统计访客的操作系统
     */
    @GetMapping("operatingSystem")
    BaseResponse operatingSystem();

    /**
     * 统计访客的所在城市
     */
    @GetMapping("city")
    BaseResponse city();
}
