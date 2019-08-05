package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.platform.ConditionHandler;
import com.exc.codeGenerator.platform.ListHandler;
import com.exc.codeGenerator.service.CodeService;

import static com.exc.codeGenerator.platform.TransformHelper.getJavabeanFieldName;
import static com.exc.codeGenerator.platform.WriteHelper.appendToFile;

/**
 * 生成更新语句的业务类
 * @author cdx
 * @date 2019-08-05
 */
public class UpdateServiceImpl implements CodeService {
    @Override
    public void execute(RequestParam param) {
        // 拼凑mybatis的更新sql语句
        StringBuilder updateSql = new StringBuilder();
        String[] fieldList = ListHandler.handle(param.getFieldList());
        if (fieldList == null) {
            return;
        }
        String[] conditionList = {};
        if (!"".equals(param.getConditionList())) {
            if (param.getConditionList().contains(",")) {
                conditionList = param.getConditionList().split(",");
            }else {
                conditionList = new String[]{param.getConditionList()};
            }
        }

        updateSql.append("\t<update id=\"\" parameterType=\"\" >\r\n")
                .append("\t\tUPDATE\r\n\t\t")
                .append(param.getTableName()).append("\r\n")
                .append("\t\t<trim prefix=\"SET\" suffixOverrides=\",\">\r\n");

        if (fieldList.length != 0) {
            for (int i=0; i<fieldList.length; i++) {
                String field = fieldList[i];
                String javabeanField = getJavabeanFieldName(field);
                if (!"".equals(field.trim())) {
                    updateSql.append("\t\t\t<if test=\"")
                            .append(javabeanField.trim())
                            .append(" != null\">\r\n\t\t\t\t")
                            .append(field.trim())
                            .append(" = #{")
                            .append(javabeanField);

                    if (i != fieldList.length -1){
                        updateSql.append("},\r\n");
                    }else {
                        updateSql.append("}\r\n");
                    }

                    updateSql.append("\t\t\t</if>\r\n");


                }
            }

            updateSql.append("\t\t</trim>\r\n");
        } else {
            return;
        }

        if (conditionList.length != 0) {
            ConditionHandler.handle(updateSql, conditionList);
            updateSql.append("\t</update>\r\n");
        } else {
            updateSql.append("\r\n</update>\r\n");
        }

        appendToFile(updateSql, param.getFilePath());
    }
}
