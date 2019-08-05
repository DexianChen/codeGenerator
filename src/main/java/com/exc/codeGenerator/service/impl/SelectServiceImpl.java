package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.service.CodeService;
import lombok.extern.slf4j.Slf4j;

import static com.exc.codeGenerator.platform.TransformHelper.getJavabeanFieldName;
import static com.exc.codeGenerator.platform.WriteHelper.appendToFile;

/**
 * 生成查询语句的业务类
 * @author cdx
 * @date 2019-08-05
 */
@Slf4j
public class SelectServiceImpl implements CodeService {

    @Override
    public void execute(RequestParam param) {
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
                        .append("} AND\r\n\t\t\t</if>\r\n");
            }

            sql.append("\t\t</where>\r\n");
            sql.append("\t</select>\r\n");
        } else {
            sql.append("\r\n</select>\r\n");
        }
        log.info("查询语句为：{}", sql.toString());

        appendToFile(sql, param.getFilePath());
    }
}
