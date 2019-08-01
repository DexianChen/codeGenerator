package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.service.CodeService;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class CodeServiceImpl implements CodeService {

    @Override
    public void select(RequestParam param) {
        // 拼凑mybatis的查询sql语句
        StringBuilder sql = new StringBuilder();
        String[] conditionList = {};
        if (!"".equals(param.getConditionList())) {
            if (param.getConditionList().contains(",")) {
                conditionList = param.getConditionList().split(",");
            }else {
                conditionList[0] = param.getConditionList();
            }
        }

        sql.append("\t<select id=\"\" parameterType=\"\" resultMap=\"\" >\r\n")
                .append("\t\tSELECT\r\n\t\t")
                .append(param.getFieldList())
                .append("\r\n\t\tFROM\r\n\t\t")
                .append(param.getTableName());

        if (conditionList.length != 0) {
            sql.append("\r\n\t\t<where>\r\n");

            for (String conditionField : conditionList) {
                String javabeanField = getJavabeanFieldName(conditionField);

                sql.append("\t\t\t<if test=\"")
                        .append(javabeanField)
                        .append(" != null\">\r\n\t\t\t\t")
                        .append(conditionField.trim())
                        .append(" = #{")
                        .append(javabeanField)
                        .append("}\r\n\t\t\t</if>\r\n");
            }

            sql.append("\t\t</where>\r\n");
            sql.append("\t</select>\r\n");
        } else {
            sql.append("\r\n</select>\r\n");
        }
        log.info("查询语句为：{}", sql.toString());

        appendToFile(sql, param.getFilePath());
    }

    @Override
    public void insert(RequestParam param) {
        // 拼凑mybatis的查询sql语句
        StringBuilder sql = new StringBuilder();
        String[] conditionList = {};
        if (!"".equals(param.getConditionList())) {
            conditionList = param.getConditionList().split(",");
        }

        sql.append("\t<insert id=\"\" parameterType=\"\" >\r\n")
                .append("\t\tINSERT INTO\r\n\t\t")
                .append(param.getTableName()).append("\r\n")
                .append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\r\n")
                .append("id\r\n");

        if (databaseKeys.length != 0) {
            for (int i=0; i<databaseKeys.length; i++){
                if (!"".equals(databaseKeys[i].trim())) {
                    sql.append("\t\t\t<if test=\"")
                            .append(databaseKeys[i].trim())
                            .append(" != null\">\r\n\t\t\t\t")
                            .append(requestKeys[i].trim())
                            .append("\r\n")
                            .append("</if>\r\n");
                }
            }

            sql.append("\t\t</trim>\r\n");
        } else {
            return;
        }

        sql.append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\r\n").append("NULL,\r\n");

        if (conditionList.length != 0) {
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
        } else {
            return;
        }

//        log.info("插入语句为：{}", sql.toString());
        System.out.println(sql);
        appendToFile(sql, param.getFilePath());
    }

    /**
     * 将sql语句追加到文件中
     *
     * @param sql      sql语句
     * @param filePath 文件路径
     */
    private void appendToFile(StringBuilder sql, String filePath) {
        // 追加到文件
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // 判断是否为空文件
            String firstLine = reader.readLine();
            if (firstLine == null) {
                // 空文件，需要先增加XML声明
                builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n")
                        .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" " +
                                "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n")
                        .append("<mapper namespace=\"\">\r\n")
                        .append(sql)
                        .append("</mapper>");
            } else {
                builder.append(firstLine).append("\r\n");
                String str;
                while ((str = reader.readLine()) != null) {
                    if (str.endsWith("</mapper>") || builder.toString().endsWith("</mapper>")) {
                        builder.append(sql);
                        builder.append(str);
                    } else {
                        builder.append(str).append("\r\n");
                    }
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.flush();
            writer.write(builder.toString());

            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据库字段和javabean字段进行映射
     * @param databaseFieldName 数据库字段
     * @return javabean字段
     */
    private String getJavabeanFieldName(String databaseFieldName) {
        StringBuilder sb = new StringBuilder();

        String[] words ={};
        if (databaseFieldName.trim().contains("_")){
            words = databaseFieldName.trim().split("_");
        }else {
            return databaseFieldName.trim();
        }

        for (int j = 0; j < words.length; j++){
            if (j != 0) {
                sb.append(words[j].substring(0 ,1).toUpperCase());
                sb.append(words[j].substring(1));
            }else {
                sb.append(words[j]);
            }
        }

        return sb.toString();
    }
}
