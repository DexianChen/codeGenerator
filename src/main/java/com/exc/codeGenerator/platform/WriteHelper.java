package com.exc.codeGenerator.platform;

import java.io.*;

/**
 * 文件输出帮助类
 * @author cdx
 * @date 2019-08-05
 */
public class WriteHelper {
    /**
     * 将sql语句追加到文件中
     * @param sql sql语句
     * @param filePath 文件路径
     */
    public static void appendToFile(StringBuilder sql, String filePath) {
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
}
