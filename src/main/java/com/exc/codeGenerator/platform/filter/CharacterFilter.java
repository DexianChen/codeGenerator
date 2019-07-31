package com.exc.codeGenerator.platform.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CharacterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //处理全局乱码

        //1.将父类req和resp转出子类
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //2.获取请求方法,针对不同的方法进行处理乱码
        String method = request.getMethod();//get或post
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }else{
            //处理get乱码,服务器获取的数据,服务器通过iso8859-1解码就乱了
            //可以在这里倒推解决吗?
//            String name = request.getParameter("name");
//            name = new String(name.getBytes("iso8859-1"),"utf-8");
//          这里倒推解决乱码没用,在servlet里面使用request获取数据依然是乱码
            //我们应该保证request在servlet里面获取get数据不乱码,怎么办?
            //增强reqeust.getParameter方法,让servlet里面调用这个方法时调用增强的方法.
            //创建装饰类对request进行增强,放行是传递装饰类对象,在servlet里面就会调用装饰类对象的增强方法
            request = new MyRequest(request);
        }

        //放行
        response.setContentType("text/html;charset=utf-8");//解决响应乱码
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

//1.创建一个类,继承具体对象
class MyRequest extends HttpServletRequestWrapper {
    //2.定义一个抽象成员对象,用于接收具体增强的对象,通过构造方法初始化
    private HttpServletRequest request;
    public MyRequest(HttpServletRequest request){
        super(request);
        this.request = request;
    }
    //3.重写增强方法,进行增强


    @Override
    public String getParameter(String name) {
        //对具体对象进行增强
        String value = request.getParameter(name);
        //将乱码进行对推解决
        if(value!=null && !"".equals(value)){
            try {
                value = new String(value.getBytes("iso8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
