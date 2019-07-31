package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.service.CodeService;

import java.io.*;

public class CodeServiceImpl implements CodeService {

    @Override
    public void select(RequestParam param) {
        // 拼凑mybatis的sql语句
        StringBuilder sql = new StringBuilder();
        String[] databaseKeys = {};
        String[] requestKeys = {};
        if (!"".equals(param.getDatabaseKeys()) && !"".equals(param.getRequestKeys())) {
            databaseKeys = param.getDatabaseKeys().split(",");
            requestKeys = param.getRequestKeys().split(",");
        }

        sql.append("\t<select id=\"\" parameterType=\"\" resultMap=\"\" >\r\n")
                .append("\t\tSELECT\r\n\t\t")
                .append(param.getFieldList())
                .append("\r\n\t\tFROM\r\n\t\t")
                .append(param.getTableName());

        if (databaseKeys.length != 0 && databaseKeys.length == requestKeys.length) {
            sql.append("\r\n\t\t<where>\r\n");

            for (int i=0; i<databaseKeys.length; i++){
                if (!"".equals(databaseKeys[i].trim()) && !"".equals(requestKeys[i].trim())) {
                    sql.append("\t\t\t<if test=\"")
                            .append(databaseKeys[i].trim())
                            .append(" != null\">\r\n\t\t\t\t")
                            .append(databaseKeys[i].trim())
                            .append(" = #{")
                            .append(requestKeys[i].trim())
                            .append("}\r\n\t\t\t</if>\r\n");
                }
            }
            
            sql.append("\t\t</where>\r\n");
            sql.append("\t</select>\r\n");
        } else {
            sql.append("\r\n</select>\r\n");
        }
        System.out.println(sql);

        // 追加到文件
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(param.getFilePath()));

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
            }else {
                builder.append(firstLine).append( "\r\n");
                String str;
                while ((str = reader.readLine()) != null) {
                    if (str.endsWith("</mapper>") || builder.toString().endsWith("</mapper>")){
                        builder.append(sql);
                        builder.append(str);
                    }else {
                        builder.append(str).append("\r\n");
                    }
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(param.getFilePath()));
            writer.flush();
            writer.write(builder.toString());

            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
