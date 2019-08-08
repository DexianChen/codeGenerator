package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.platform.TransformHelper;

import java.util.List;

import static com.exc.codeGenerator.platform.WriteHelper.appendToFile;

/**
 * @author cdx
 * date 2019-08-06
 */
public class ResultMapServiceImpl {

    // TODO
    public static void writeResultMap(List<String> fieldNameList, List<String> fieldTypeList, String filePath) {
        // 拼凑mybatis的resultMap语句
        StringBuilder sql = new StringBuilder();

        sql.append("\r\n\t<resultMap id=\"\" type=\"\" >");
        for (int i=0; i<fieldNameList.size(); i++) {
            String fieldName = fieldNameList.get(i);
            String fieldType = fieldTypeList.get(i);
            if (i==0){
                sql.append("\r\n\t\t<id column=\"")
                        .append(fieldName)
                        .append("\" property=\"")
                        .append(TransformHelper.getJavabeanFieldName(fieldName))
                        .append("\" jdbcType=\"")
                        .append(fieldType)
                        .append("\" />");
            }else {
                sql.append("\r\n\t\t<result column=\"")
                        .append(fieldName)
                        .append("\" property=\"")
                        .append(TransformHelper.getJavabeanFieldName(fieldName))
                        .append("\" jdbcType=\"")
                        .append(fieldType)
                        .append("\" />");
            }
        }
        sql.append("\r\n\t</resultMap>\r\n");

        appendToFile(sql,filePath);
    }
}
