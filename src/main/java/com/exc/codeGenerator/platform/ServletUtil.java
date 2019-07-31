package com.exc.codeGenerator.platform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@Slf4j
public class ServletUtil {
    /**
     * 将post请求过来的json数据分装到实体类中
     * @param request 请求
     * @return user 用户实体类
     */
    public static <T> T getRequestPostStr(HttpServletRequest request, Class<T> Clazz) {
        T t = null;
        try {
            BufferedReader streamReader = new BufferedReader(
                    new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            log.info("前端传入的参数为：{}", responseStrBuilder.toString());
            t = JSON.parseObject(responseStrBuilder.toString(), Clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将实体类转换为json字符串返回到前端页面
     * @param response
     * @param object
     * @throws IOException
     */
    public static void sendData(HttpServletResponse response, Object object) {
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            response.setContentType("application/json; charset=utf-8");
            String jsonString = JSONObject.toJSONString(object);
            out.print(jsonString);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
