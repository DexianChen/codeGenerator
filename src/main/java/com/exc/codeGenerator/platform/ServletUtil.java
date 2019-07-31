package com.exc.codeGenerator.platform;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
}
