package com.exc.codeGenerator.web;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.service.CodeService;
import com.exc.codeGenerator.service.impl.CodeServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.exc.codeGenerator.platform.ServletUtil.getRequestPostStr;

/**
 * 获取
 * @author ChenDexian
 * @date 2019-7-9
 */
public class SelectServlet extends HttpServlet {
    private CodeService codeService = new CodeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        RequestParam param = getRequestPostStr(request, RequestParam.class);
        System.out.println(param.toString());
        codeService.select(param);
    }
}
