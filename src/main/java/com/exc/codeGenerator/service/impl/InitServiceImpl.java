package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.dao.CodeDao;
import com.exc.codeGenerator.model.InitRequestParam;

import java.sql.SQLException;
import java.util.*;

/**
 * 初始化业务类，获取对应的表中的字段列表
 * @author cdx
 * @date 2019-08-05
 */
public class InitServiceImpl {
    private static CodeDao codeDao = new CodeDao();

    public static Map<String, List<?>> getFieldList(InitRequestParam param) {
        Map<String, List<?>> map = new HashMap<>();

        List<String> fieldList = new LinkedList<>();
        List<String> typeList = new LinkedList<>();

//        Map<String, Object> FieldMap = null;
//        try {
////            FieldMap = codeDao.getFieldList(param);
//            for (Map.Entry<String, Object> entry : FieldMap.entrySet()) {
//                fieldList.add(entry.getKey());
//
//                // 判断数据类型
//                String typeName = entry.getValue().getClass().getTypeName();
//                switch (typeName) {
//                    case "java.lang.Long":
//                        typeList.add("BIGINT");
//                        break;
//                    case "java.lang.String":
//                        typeList.add("VARCHAR");
//                        break;
//                    case "java.lang.Integer":
//                        typeList.add("INTEGER");
//                        break;
//                    case "java.util.Date":
//                        typeList.add("TIMESTAMP");
//                        break;
//                }
//            }
//
//            map.put("fieldList", fieldList);
//            map.put("typeList", typeList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return map;
    }
}
