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
    public static void writeResultMap(List<String> fieldList, String filePath) {
        // 拼凑mybatis的resultMap语句
        StringBuilder sql = new StringBuilder();

        sql.append("\r\n\t<resultMap id=\"\" type=\"\" >");
        for (int i=0; i<fieldList.size(); i++) {
            String field = fieldList.get(i);
            if (i==0){
                sql.append("\r\n\t\t<id column=\"")
                        .append(field)
                        .append("\" property=\"")
                        .append(TransformHelper.getJavabeanFieldName(field))
                        .append("\" jdbcType=\"INTEGER\" />");
            }else {
                sql.append("\r\n\t\t<result column=\"")
                        .append(field)
                        .append("\" property=\"")
                        .append(TransformHelper.getJavabeanFieldName(field))
                        .append("\" jdbcType=\"INTEGER\" />");
            }
        }
        sql.append("\r\n\t</resultMap>\r\n");

        appendToFile(sql,filePath);
    }
}
