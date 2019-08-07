import com.exc.codeGenerator.dao.CodeDao;
import com.exc.codeGenerator.model.InitRequestParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class CodeDaoTest {
    private CodeDao codeDao = new CodeDao();
    
    @Test
    public void testCodeDao() throws SQLException {
        InitRequestParam param = new InitRequestParam();
        param.setAddress("localhost");
        param.setPort("3306");
        param.setDatabaseName("mall");
        param.setUser("root");
        param.setPassword("123456");
        param.setTableName("pms_product");

        List<Map<String, Object>> resultList = codeDao.getFieldList(param);
        for (Map<String, Object> map : resultList) {
            Object columnName = map.get("columnName");
            Object dataType = map.get("dataType");

            System.out.println(columnName.toString() + dataType.toString());
        }

    }
}
