package com.exc.codeGenerator.dao;

import com.exc.codeGenerator.model.InitRequestParam;
import com.exc.codeGenerator.platform.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author cdx
 * date: 2019/08/04
 */
public class CodeDao {

    public Map<String, Object> getFieldList(InitRequestParam param) throws SQLException {
        C3P0Util c3P0Util = new C3P0Util(param);
        QueryRunner runner = new QueryRunner(c3P0Util.getDataSource());

        String sql = "select * from " + param.getTableName() + " limit 1";
        Map<String, Object> fieldMap = runner.query(sql, new MapHandler());
        return fieldMap;
    }
}
