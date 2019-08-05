package com.exc.codeGenerator.web;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.service.CodeService;
import com.exc.codeGenerator.service.impl.InsertServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.exc.codeGenerator.platform.ServletUtil.getRequestPostStr;

/**
 * 插入
 * @author ChenDexian
 * @date 2019-7-9
 */
public class InsertServlet extends HttpServlet {
    private CodeService codeService = new InsertServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        RequestParam param = getRequestPostStr(request, RequestParam.class);
        codeService.execute(param);
    }
}
