package com.chens.coblog.api.front;


import com.alibaba.fastjson.JSONObject;
import com.chens.coblog.common.base.BaseResponse;
import com.chens.coblog.common.utils.HttpRequestUtils;
import com.chens.coblog.config.GithubConfig;
import com.chens.coblog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台应用控制器
 * @author chens
 * @version 1.0.0
 * @date 2020/8/12
 */
@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private ConfigService configService;

    @GetMapping("global")
    public BaseResponse global() {
        return BaseResponse.success("查询成功", configService.getAllGlobal());
    }

    @GetMapping("github")
    public BaseResponse sendCode() {
        return BaseResponse.success("获取成功",GithubConfig.CODE_URL);
    }

    @GetMapping("userInfo")
    public BaseResponse getInfo(@RequestParam(value = "token") String token) {
        try {
            String userInfo = GithubConfig.getUerInfoUrl(token);
            //封装数据
            JSONObject obj= JSONObject.parseObject(userInfo);
            Map<String,Object> info = new HashMap<>(3);
            info.put("name", obj.get("name")+"");
            info.put("id", obj.get("id")+"");
            info.put("avatar", obj.get("avatar_url")+"");
            return BaseResponse.success("获取成功", info);
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponse.success("没有信息");
        }
    }

    @GetMapping("callback")
    public String callBack(HttpServletRequest request){
        String code = request.getParameter("code");
        //申请令牌
        String result = HttpRequestUtils.sendGet( GithubConfig.getTokenUrl(code) );
        //从result中截取令牌
        String accessToken = HttpRequestUtils.getMap(result).get("access_token");
        return "<script>" +
                    "window.opener.postMessage('"+ accessToken +"', '*' );" +
                    "window.close();" +
                "</script>";
    }

    /**
     * 搜索页分页
     * @param keyword  关键字
     * @param pageIndex 需要加载的页码
     */
    @GetMapping("{keyword}/{pageIndex}")
    public BaseResponse search(Model model, @PathVariable(value = "keyword") String keyword, @PathVariable(value = "pageIndex") Integer pageIndex) {

        return null;
    }

}
