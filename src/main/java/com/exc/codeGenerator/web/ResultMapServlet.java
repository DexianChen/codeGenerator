package com.exc.codeGenerator.web;

import com.alibaba.fastjson.JSONArray;
import com.exc.codeGenerator.service.impl.ResultMapServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * 生成resultMap标签
 * @author cdx
 * date 2019-08-06
 */
public class ResultMapServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String fieldNames = request.getParameter("fieldNames");
        List<String> fieldList = JSONArray.parseArray(fieldNames, String.class);
        String filePath = request.getParameter("filePath");
        ResultMapServiceImpl.writeResultMap(fieldList, filePath);
    }
}
