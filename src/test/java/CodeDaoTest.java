import com.exc.codeGenerator.dao.CodeDao;
import com.exc.codeGenerator.model.InitRequestParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
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
        param.setTableName("oms_cart_item");

        Map<String, Object> resultMap = codeDao.getFieldList(param);
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
