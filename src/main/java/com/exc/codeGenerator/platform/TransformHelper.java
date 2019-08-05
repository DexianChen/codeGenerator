package com.exc.codeGenerator.platform;

/**
 * 字段转换辅助类
 * @author cdx
 * @date 2019-08-05
 */
public class TransformHelper {
    /**
     * 将数据库字段和javabean字段进行映射
     * @param databaseFieldName 数据库字段
     * @return javabean字段
     */
    public static String getJavabeanFieldName(String databaseFieldName) {
        StringBuilder sb = new StringBuilder();

        String[] words ={};
        if (databaseFieldName.trim().contains("_")){
            words = databaseFieldName.trim().split("_");
        }else {
            return databaseFieldName.trim();
        }

        for (int j = 0; j < words.length; j++){
            if (j != 0) {
                sb.append(words[j].substring(0 ,1).toUpperCase());
                sb.append(words[j].substring(1));
            }else {
                sb.append(words[j]);
            }
        }

        return sb.toString();
    }
}
