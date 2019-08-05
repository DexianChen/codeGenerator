package com.exc.codeGenerator.web;

import com.exc.codeGenerator.model.InitRequestParam;
import com.exc.codeGenerator.service.CodeService;
import com.exc.codeGenerator.service.impl.InitServiceImpl;
import com.exc.codeGenerator.service.impl.SelectServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

import static com.exc.codeGenerator.platform.ServletUtil.getRequestPostStr;
import static com.exc.codeGenerator.platform.ServletUtil.sendData;

/**
 * 初始化方法，从对应数据库表中获取字段列表
 *
 * @author cdx
 * date: 2019/08/04
 */
public class InitServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        InitRequestParam param = getRequestPostStr(request, InitRequestParam.class);
        List<String> fieldList = InitServiceImpl.getFieldList(param);
        sendData(response, fieldList);
    }
}