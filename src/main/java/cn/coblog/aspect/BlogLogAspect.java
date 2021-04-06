package cn.coblog.aspect;

import cn.coblog.domain.Log;
import cn.coblog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 访客日志，切面处理类
 * @author chens
 * @date 2018/12/30
 */
@Slf4j
@Aspect
@Component
public class BlogLogAspect {

	@Autowired
	private LogService logService;

    @Around("execution(public * cn.coblog.api.front..*.*(..)) && !@annotation(org.springframework.web.bind.annotation.ModelAttribute)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Log blogLog = new Log();
        try{
            //调用目标方法
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            //响应时长
            int duration = Math.toIntExact(endTime - startTime);
//            blogLog.setDuration(duration);
//            //返回结果
//            blogLog.setResult(JSONObject.toJSONString(result));
//            //执行方法
//            String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
//            blogLog.setMethod(method);
//            //获取request和response
//            HttpServletRequest request=((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//            //请求参数
//            String params = JSONObject.toJSONString(request.getParameterMap());
//            blogLog.setParams(params);
//            //请求来源
//            blogLog.setReferer(request.getHeader("referer"));
//            //浏览器
//            String userAgentStr = request.getHeader("user-agent");
//            blogLog.setUserAgent(userAgentStr);
//            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
//            String operatingSystem= userAgent.getOperatingSystem().getName();
//            blogLog.setOperatingSystem(operatingSystem);
//            String browser = userAgent.getBrowser().getName();
//            blogLog.setBrowser(browser);
//            //请求类型
//            blogLog.setType(request.getMethod());
//            //IP地址
//            String ip= IPUtils.getIpAddr(request);
//            blogLog.setIp(ip);
//            //所在城市
//            blogLog.setCity(IPUtils.getCity(ip));
//            if(StringUtils.isEmpty(blogLog.getCity())||"0".equals(blogLog.getCity())){
//                blogLog.setCity("未知");
//            }
//            //访问链接
//            blogLog.setUrl(request.getRequestURL().toString());
//            //判断访问是否正常返回
//            boolean isNormal = true;
//            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//            if(Objects.nonNull(response) && response.getStatus() != HttpServletResponse.SC_OK){
//                isNormal = false;
//            }else if(result instanceof BaseResponse && ((BaseResponse) result).getCode() != CodeEnum.SUCCESS.code()){
//                isNormal = false;
//            }
//            blogLog.setIsNormal(isNormal);
//            log.info("访问日志：{}", JSONObject.toJSONString(blogLog));
//            logService.addLog(blogLog);
            return result;
        }catch (Throwable t){
            log.error("aop方法执行失败",t);
            blogLog.setIsNormal(false);
            logService.addLog(blogLog);
            throw t;
        }
	}
}
