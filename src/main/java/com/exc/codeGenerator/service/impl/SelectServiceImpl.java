package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.platform.ConditionHandler;
import com.exc.codeGenerator.platform.ListHandler;
import com.exc.codeGenerator.service.CodeService;

import static com.exc.codeGenerator.platform.TransformHelper.getJavabeanFieldName;
import static com.exc.codeGenerator.platform.WriteHelper.appendToFile;

/**
 * 生成查询语句的业务类
 * @author cdx
 * @date 2019-08-05
 */
public class SelectServiceImpl implements CodeService {

    @Override
    public void execute(RequestParam param) {
        // 拼凑mybatis的查询sql语句
        StringBuilder selectSql = new StringBuilder();
        String[] conditionList = ListHandler.handle(param.getConditionList());
        if (conditionList == null) {
            return;
        }

        selectSql.append("\t<select id=\"\" parameterType=\"\" resultMap=\"\" >\r\n")
                .append("\t\tSELECT\r\n\t\t")
                .append(param.getFieldList())
                .append("\r\n\t\tFROM\r\n\t\t")
                .append(param.getTableName());

        if (conditionList.length != 0) {
            ConditionHandler.handle(selectSql, conditionList);
            selectSql.append("\t</select>\r\n");
        } else {
            selectSql.append("\r\n</select>\r\n");
        }

        appendToFile(selectSql, param.getFilePath());
    }
}
