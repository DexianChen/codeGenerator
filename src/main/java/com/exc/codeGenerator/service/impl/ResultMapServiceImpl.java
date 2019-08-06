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
            String field = fieldList.get(0);

            if (i==0){
                sql.append("<id column=\"")
                        .append(field)
                        .append("\" property=\"")
                        .append(TransformHelper.getJavabeanFieldName(field))
                        .append("\" jdbcType=\"INTEGER\" />");
            }else {
                sql.append("<result column=\"")
                        .append(field)
                        .append("\" property=\"")
                        .append(TransformHelper.getJavabeanFieldName(field))
                        .append("\" jdbcType=\"INTEGER\" />");
            }
        }
        sql.append("\r\n\t</resultMap>");

        appendToFile(sql,filePath);
    }
}
