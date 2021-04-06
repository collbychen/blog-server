package cn.coblog.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chens
 * @version 1.0.0
 * @date 2020/8/29 8:49
 * */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(data.toString());
        out.flush();
        out.close();
    }

    public static void writeJson(HttpServletResponse response, Object data)throws IOException{
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println(new ObjectMapper().writeValueAsString(data));
        out.flush();
        out.close();
    }
}
