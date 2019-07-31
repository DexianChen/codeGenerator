package com.exc.codeGenerator.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于读取resources目录下的sql文件，将sql语句抽取到文件中
 * @author ChenDexian
 * @date 2019-7-9
 */
public class ReadSQLFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReadSQLFileUtil.class);

    /**
     * 输入sql文件名以及sql语句的key返回sql语句，如：
     * insertUser=insert into tb_user values(NULL,?,?,?,?,?,?,?,?,?)
     * @param sqlName sql语句的key
     * @return sql语句
     */
    public static String readSqlFile(String fileName, String sqlName){
        if ("".equals(fileName)){
            logger.error("读取sql文件名为空");
            return null;
        }

        if ("".equals(sqlName)){
            logger.error("读取sql文件时，输入的sql语句的key为空");
            return null;
        }

        List<String> sqlList = new ArrayList<>();
        try {
            String path = ReadSQLFileUtil.class.getClassLoader().getResource("sql/" + fileName + ".sql").getPath();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
            String sqlStr;
            while ((sqlStr = bufferedReader.readLine()) != null) {
                // 跳过注释行
                if (!sqlStr.startsWith("//")){
                    sqlList.add(sqlStr);
                }
            }

            for (String sql : sqlList) {
                // 获取第一个等号的索引
                int index = sql.indexOf("=");
                // 获取sql语句的key
                String sqlKey = sql.substring(0, index).trim();

                if (sqlName.equals(sqlKey)){
                    // 返回sql语句
                    return sql.substring(index+1).trim();
                }
            }
        } catch (IOException e) {
            logger.error("读取sql文件时出错：" + e);
        }

        return null;
    }
}
