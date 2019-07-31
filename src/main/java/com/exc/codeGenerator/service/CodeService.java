package com.exc.codeGenerator.service;


import com.exc.codeGenerator.model.RequestParam;

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
}
