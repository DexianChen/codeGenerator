package com.exc.codeGenerator.service;


import com.exc.codeGenerator.model.InitRequestParam;
import com.exc.codeGenerator.model.RequestParam;

import java.sql.SQLException;
import java.util.List;

/**
 * 业务接口
 * @author ChenDexian
 * @date 2091-7-9
 */
public interface CodeService {

    /**
     * 查询
     * @param param
     */
    void select(RequestParam param);

    /**
     * 插入
     * @param param
     */
    void insert(RequestParam param);

    /**
     * 初始化方法，获取对应的表中的字段列表
     * @param param
     * @return
     */
    List<String> getFieldList(InitRequestParam param) throws SQLException;
}
