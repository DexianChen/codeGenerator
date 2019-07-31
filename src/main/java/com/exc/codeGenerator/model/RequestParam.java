package com.exc.codeGenerator.model;

import lombok.Data;

@Data
public class RequestParam {
    /**
     * 操作，增删改查
     */
    private String operation;
    /**
     * 字段列表, 以逗号分隔
     */
    private String fieldList;
    /**
     * 表名
     */
    private String tableName;
    /**
     * where 条件, key为数据库字段名列表，以逗号分隔
     */
    private String databaseKeys;
    /**
     * where 条件, value为输入的字段名列表，以逗号分隔
     */
    private String requestKeys;
    /**
     * 选择需要添加代码的文件路径
     */
    private String filePath;
}
