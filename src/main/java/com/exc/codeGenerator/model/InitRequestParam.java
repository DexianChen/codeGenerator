package com.exc.codeGenerator.model;

import lombok.Data;

/**
 * 初始化请求参数
 * @author cdx
 * date: 2019/08/04
 */
@Data
public class InitRequestParam {
    private String address;
    private String port;
    private String databaseName;
    private String user;
    private String password;
    private String tableName;
}
