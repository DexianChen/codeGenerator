package com.exc.codeGenerator.web;

import com.alibaba.fastjson.JSONArray;
import com.exc.codeGenerator.model.ResultMapRequestParam;
import com.exc.codeGenerator.service.impl.ResultMapServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.exc.codeGenerator.platform.ServletUtil.getRequestPostStr;

/**
 * 生成resultMap标签
 * @author cdx
 * date 2019-08-06
 */
public class ResultMapServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<String> fieldList = JSONArray.parseArray(request.getParameter("fieldNames"), String.class);
        String filePath = request.getParameter("filePath");
        ResultMapServiceImpl.writeResultMap(fieldList, filePath);
    }
}
