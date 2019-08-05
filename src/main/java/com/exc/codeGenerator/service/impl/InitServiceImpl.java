package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.dao.CodeDao;
import com.exc.codeGenerator.model.InitRequestParam;
import com.exc.codeGenerator.service.CodeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 初始化业务类，获取对应的表中的字段列表
 * @author cdx
 * @date 2019-08-05
 */
public class InitServiceImpl {
    private static CodeDao codeDao = new CodeDao();

    public static List<String> getFieldList(InitRequestParam param) {
        List<String> fieldList = new ArrayList<>();

        Map<String, Object> FieldMap = null;
        try {
            FieldMap = codeDao.getFieldList(param);
            for (Map.Entry<String, Object> entry : FieldMap.entrySet()) {
                fieldList.add(entry.getKey());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fieldList;
    }
}
