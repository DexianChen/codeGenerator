package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.platform.ListHandler;
import com.exc.codeGenerator.service.CodeService;

import static com.exc.codeGenerator.platform.TransformHelper.getJavabeanFieldName;
import static com.exc.codeGenerator.platform.WriteHelper.appendToFile;

/**
 * 生成插入语句的业务类
 * @author cdx
 * @date 2019-08-05
 */
public class InsertServiceImpl implements CodeService {

    @Override
    public void execute(RequestParam param) {
        // 拼凑mybatis的插入sql语句
        StringBuilder sql = new StringBuilder();
        String[] conditionList = ListHandler.handle(param.getConditionList());
        if (conditionList == null) {
            return;
        }

        sql.append("\t<insert id=\"\" parameterType=\"\" >\r\n")
                .append("\t\tINSERT INTO\r\n\t\t")
                .append(param.getTableName()).append("\r\n")
                .append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\r\n")
                .append("id\r\n");

        if (conditionList.length != 0) {
            for (String conditionField : conditionList) {
                String javabeanField = getJavabeanFieldName(conditionField);
                if (!"".equals(conditionField.trim())) {
                    sql.append("\t\t\t<if test=\"")
                            .append(javabeanField.trim())
                            .append(" != null\">\r\n\t\t\t\t")
                            .append(conditionField.trim())
                            .append("\r\n")
                            .append("</if>\r\n");
                }
            }

            sql.append("\t\t</trim>\r\n");
        } else {
            return;
        }

        sql.append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\r\n").append("NULL,\r\n");

        for (String conditionField : conditionList) {
            String javabeanField = getJavabeanFieldName(conditionField);

            if (!"".equals(conditionField.trim())) {
                sql.append("\t\t\t<if test=\"")
                        .append(javabeanField.trim())
                        .append(" != null\">\r\n\t\t\t\t")
                        .append("#{")
                        .append(javabeanField.trim())
                        .append("}\r\n")
                        .append("</if>\r\n");
            }
        }
        sql.append("\t\t</trim>\r\n");
        sql.append("\t</insert>\r\n");

        appendToFile(sql, param.getFilePath());
    }
}
