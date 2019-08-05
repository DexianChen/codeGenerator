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
     * 执行业务方法
     * @param param 请求参数
     */
    void execute(RequestParam param);
}
