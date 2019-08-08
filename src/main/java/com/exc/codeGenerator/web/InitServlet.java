package com.exc.codeGenerator.web;

import com.exc.codeGenerator.model.InitRequestParam;
import com.exc.codeGenerator.service.impl.InitServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import static com.exc.codeGenerator.platform.ServletUtil.getRequestPostStr;
import static com.exc.codeGenerator.platform.ServletUtil.sendData;

/**
 * 初始化方法，从对应数据库表中获取字段列表及其字段类型列表
 *
 * @author cdx
 * date: 2019/08/04
 */
public class InitServlet extends HttpServlet {
    private InitServiceImpl initService = new InitServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        InitRequestParam param = getRequestPostStr(request, InitRequestParam.class);
        Map<String,List<String>> resultMap = initService.getFieldList(param);
        sendData(response, resultMap);
    }
}