package com.exc.codeGenerator.platform;

import java.util.List;

/**
 * 处理前端输入的字段列表, 如:fieldList, conditionList
 * @author cdx
 * @date 2019-08-05
 */
public class ListHandler {

    public static String[] handle(String listStr) {
        String[] array = {};
        if (!"".equals(listStr)) {
            if (listStr.contains(",")) {
                array = listStr.split(",");
            }else {
                array = new String[]{listStr};
            }
            return array;
        }else {
            return null;
        }
    }
}
