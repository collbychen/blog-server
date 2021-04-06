package cn.coblog.api.imp;

import cn.coblog.api.admin.LogApi;
import cn.coblog.common.base.BaseResponse;
import cn.coblog.domain.Log;
import cn.coblog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class LogImp implements LogApi {

    @Autowired
    private LogService logService;

    @Override
    public BaseResponse visitsCount(){
        Map<String,Integer> data=new HashMap<>();
        int totalVisits = logService.getTotalCount();
        data.put("totalVisits",totalVisits);
        int latestVisits=logService.getOneWeekCount();
        data.put("latestVisits",latestVisits);
        return BaseResponse.success("获取成功",data);
    }

    @Override
    public BaseResponse latest(int number){
        List<Log> logList=logService.findLatestLog(number);
        return BaseResponse.success("获取成功",logList);
    }

    @Override
    public BaseResponse browser(){
        return BaseResponse.success("获取成功",logService.statBrowser());
    }

    @Override
    public BaseResponse operatingSystem(){
        return BaseResponse.success("获取成功",logService.statOperatingSystem());
    }

    @Override
    public BaseResponse city(){
        return BaseResponse.success("获取成功",logService.statCity());
    }

}
