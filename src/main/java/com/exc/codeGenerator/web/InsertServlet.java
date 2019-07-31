package com.exc.codeGenerator.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 插入
 * @author ChenDexian
 * @date 2019-7-9
 */
public class InsertServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("insert");
    }
}
