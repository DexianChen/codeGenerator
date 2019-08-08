package com.exc.codeGenerator.service;


import com.exc.codeGenerator.model.InitRequestParam;
import com.exc.codeGenerator.service.impl.InitServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author cdx
 * date 2019-08-08
 */
@RunWith(JUnit4.class)
public class InitServiceTest {
    @Test
    public void testInitServlet() {
        InitRequestParam param = new InitRequestParam();
        param.setAddress("localhost");
        param.setPort("3306");
        param.setDatabaseName("mall");
        param.setUser("root");
        param.setPassword("123456");
        param.setTableName("pms_product");

        Map<String, List<String>> resultMap = new InitServiceImpl().getFieldList(param);
        List<String> fieldTypeList = resultMap.get("fieldTypeList");
        assertEquals("BIGINT", fieldTypeList.get(0));
    }
}
