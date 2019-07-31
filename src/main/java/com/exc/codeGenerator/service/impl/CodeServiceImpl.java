package com.exc.codeGenerator.service.impl;

import com.exc.codeGenerator.model.RequestParam;
import com.exc.codeGenerator.service.CodeService;

import java.io.*;

public class CodeServiceImpl implements CodeService {

    @Override
    public void select(RequestParam param) {
        String sql = "<select id=\"\" parameterType=\"\" resultMap=\"\" >\r\n"
                + "\tSELECT\r\n\t"
                + param.getFieldList()
                + "\r\n\tFROM\r\n\t"
                + param.getTableName()
                + "\r\n\t<where>\r\n"
                + "\t\t<if test=\""
                + param.getConditionKeyList()
                + " != null\">\r\n\t\t\t"
                + param.getConditionKeyList()
                + " = #{"
                + param.getConditionValueList()
                + "}\r\n" + "\t\t</if>\r\n" + "\t</where>\r\n" + "</select>\r\n";
        System.out.println(sql);

        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("F:\\a.txt"));

            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }

            builder.append(sql);

            BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\a.txt"));
            writer.flush();
            writer.write(builder.toString());

            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
