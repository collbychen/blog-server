package com.chens.coblog.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * github配置类
 * @author chens
 * @version 1.0.0
 * @date 2020/11/20
 */
public class GithubConfig {

    /**
     * github授权的 Client ID
     */
    private static final String CLIENT_ID = "7c657e6c919a563ee2b5";

    /**
     * github授权的 Client Secret
     */
    private static final String CLIENT_SECRET = "eea08e68a7dcd714adfab8f2944c53f855360010";

    /**
     * 结果回调地址
     */
//    private static final String CALLBACK_URL = "https://www.coblog.cn/app/github";
    private static final String CALLBACK_URL = "http://127.0.0.1:8080/app/callback";

    /**
     * 获取code的url
     */
    public static final String CODE_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID;

    /**
     * @param code 获取到的code
     * @return 获取token的url
     */
    public static String getTokenUrl(String code) {
        return "https://github.com/login/oauth/access_token?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&code="+code+"&redirect_uri="+CALLBACK_URL;
    }

    /**
     * @param token 获取到的token
     * @return 通过token获取github用户信息
     */
    public static String getUerInfoUrl(String token) throws IOException {
        String result = "";
        BufferedReader in = null;
        try {
            String baseUrl = "https://api.github.com/user";
            URL realUrl = new URL(baseUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //将token加入请求头
            connection.setRequestProperty("Authorization","token "+token);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader( connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送token请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;

    }
}
