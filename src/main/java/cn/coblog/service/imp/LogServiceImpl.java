package cn.coblog.service.imp;

import cn.coblog.domain.Log;
import cn.coblog.mapper.LogMapper;
import cn.coblog.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 日志接口实现
 * @author chens
 * @version 1.0.0
 * @date 2020/8/10
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public int getTotalCount() {
        return logMapper.selectCount(new Log());
    }

    @Override
    public int getOneWeekCount() {
        return logMapper.getTotalCount();
    }

    @Override
    public void error(String data, String message) {
    }

    @Override
    public List<Log> findLatestLog(int number) {
        return logMapper.selectLatestLog(number);
    }

    @Override
    public Map<String, Integer> statBrowser() {
        Map<String,Integer> result=new HashMap<>();
        List<String> browsers=logMapper.selectAllBrowser();
        for (String browser : browsers) {
            result.merge(browser, 1, Integer::sum);
        }
        return result;
    }

    @Override
    public Map<String, Integer> statOperatingSystem() {
        Map<String,Integer> result=new HashMap<>();
        List<String> operatingSystems=logMapper.selectAllOperatingSystem();
        for (String operatingSystem : operatingSystems) {
            result.merge(operatingSystem, 1, Integer::sum);
        }
        return result;
    }

    @Override
    public List<Map<String, Integer>> statCity() {
        return logMapper.selectCityCount();
    }

    @Override
    public void addLog(Log log) {
        if(Objects.isNull(log.getIp())) { return; }
        logMapper.insert(log);
    }

}




