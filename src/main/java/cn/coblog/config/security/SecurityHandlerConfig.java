package cn.coblog.config.security;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.common.constant.CodeEnum;
import cn.coblog.common.utils.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 验证权限处理
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 8:42
 * */

@Configuration
public class SecurityHandlerConfig {

    /**
     * 身份认证失败处理
     */
    @Bean
    public AuthenticationEntryPoint entryPointUnauthorizedHandler(){
        return (request, response, exception) -> {
            ResponseUtil.writeJson(response, BaseResponse.fail(CodeEnum.NOT_LOGIN, "请登录"));
        };
    }

    /**
     * 权限不足处理
     */
    @Bean
    public AccessDeniedHandler restAccessDeniedHandler(){
        return (request, response, exception) -> {
            ResponseUtil.writeJson(response, BaseResponse.fail(CodeEnum.FORBIDDEN, "权限不足"));
        };
    }

}
