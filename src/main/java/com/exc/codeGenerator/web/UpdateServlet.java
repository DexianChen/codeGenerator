package com.exc.codeGenerator.web;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.service.CodeService;
import com.exc.codeGenerator.service.impl.UpdateServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.exc.codeGenerator.platform.ServletUtil.getRequestPostStr;

/**
 * 更新
 * @author ChenDexian
 * @date 2019-7-9
 */
public class UpdateServlet extends HttpServlet {
    private CodeService codeService = new UpdateServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        RequestParam param = getRequestPostStr(request, RequestParam.class);
        codeService.execute(param);
    }
}
