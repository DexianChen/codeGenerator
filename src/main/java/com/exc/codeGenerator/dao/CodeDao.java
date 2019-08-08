package com.exc.codeGenerator.dao;

import com.exc.codeGenerator.model.InitRequestParam;
import com.exc.codeGenerator.platform.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author cdx
 * date: 2019/08/04
 */
public class CodeDao {

    public List<Map<String, Object>> getFieldList(InitRequestParam param) throws SQLException {
        C3P0Util c3P0Util = new C3P0Util(param);
        QueryRunner runner = new QueryRunner(c3P0Util.getDataSource());

        String sql = "select COLUMN_NAME as columnName, DATA_TYPE as dataType from information_schema.COLUMNS where table_name = '"
                + param.getTableName() +"' and table_schema = '" + param.getDatabaseName() + "';";
        return runner.query(sql, new MapListHandler());
    }
}
