package cn.coblog.filter;

import cn.coblog.common.base.BaseResponse;
import cn.coblog.common.constant.CodeEnum;
import cn.coblog.common.utils.JwtTokenUtil;
import cn.coblog.common.utils.ResponseUtil;
import cn.coblog.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WT 登陆过滤器
 * @author chens
 * @version 1.0.0
 * @date 2020/8/23
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * @param defaultFilterProcessesUrl 配置要过滤的地址，即登陆地址
     * @param authenticationManager 认证管理器，校验身份时会用到
     * */
    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        // 为 AbstractAuthenticationProcessingFilter 中的属性赋值
        setAuthenticationManager(authenticationManager);
    }

    /**
     * 提取用户账号密码进行验证
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 获取 User 对象 readValue 第一个参数 输入流，第二个参数 要转换的对象
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = user.getUsername().trim();
        username = HtmlUtils.htmlEscape(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                username,
                user.getPassword(),
                user.getAuthorities()
        );
        // 添加验证的附加信息,是否记住我
        boolean rememberMe = user.getRememberMe();
        token.setDetails(rememberMe);
        // 进行登陆验证
        return getAuthenticationManager().authenticate(token);
    }

    /**
     * 登陆成功回调
     * */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        //设置登陆成功后令牌返回
        JwtTokenUtil.addAuthentication(request, response, authResult);
    }

    /**
     * 登陆失败回调
     * */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        // 向前端写入数据
        ResponseUtil.writeJson(response, BaseResponse.fail(CodeEnum.UNKNOWN_ERROR, failed.getLocalizedMessage()));
    }

}
