package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.dao.CodeDao;
import com.exc.codeGenerator.model.InitRequestParam;

import java.sql.SQLException;
import java.util.*;

import static com.exc.codeGenerator.platform.TransformHelper.jdbcTypeMap;

/**
 * 初始化业务类，获取对应的表中的字段列表及其字段类型列表
 * @author cdx
 * @date 2019-08-05
 */
public class InitServiceImpl {
    private CodeDao codeDao = new CodeDao();

    public Map<String, List<String>> getFieldList(InitRequestParam param) {
        Map<String, List<String>> resultMap = new HashMap<>();

        List<String> fieldNameList = new LinkedList<>();
        List<String> fieldTypeList = new LinkedList<>();

        List<Map<String, Object>> resultList = null;
        try {
            // 获取数据库字段名及其字段类型列表
            resultList = codeDao.getFieldList(param);

            for (Map<String, Object> map : resultList) {
                String columnName = (String) map.get("columnName");
                fieldNameList.add(columnName);

                String dataType = (String) map.get("dataType");
                String jdbcType = jdbcTypeMap(dataType);
                fieldTypeList.add(jdbcType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resultMap.put("fieldNameList", fieldNameList);
        resultMap.put("fieldTypeList", fieldTypeList);
        return resultMap;
    }
}
