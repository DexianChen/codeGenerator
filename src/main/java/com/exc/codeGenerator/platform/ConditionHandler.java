package com.exc.codeGenerator.platform;

import static com.exc.codeGenerator.platform.TransformHelper.getJavabeanFieldName;

/**
 * 处理条件模板
 * @author cdx
 * @date 2019-08-05
 */
public class ConditionHandler {

    public static void handle(StringBuilder sql, String[] conditionList) {
        sql.append("\t\t<where>\r\n");
        for (int i=0; i<conditionList.length; i++) {
            String conditionField = conditionList[i];
            String javabeanField = getJavabeanFieldName(conditionField);

            sql.append("\t\t\t<if test=\"")
                    .append(javabeanField)
                    .append(" != null\">\r\n\t\t\t\t")
                    .append(conditionField.trim())
                    .append(" = #{")
                    .append(javabeanField);

            if (conditionList.length == 1 || i == conditionList.length-1){
                sql.append("} \r\n\t\t\t</if>\r\n");
            }else {
                sql.append("} AND\r\n\t\t\t</if>\r\n");
            }

        }
        sql.append("\t\t</where>\r\n");
    }
}
