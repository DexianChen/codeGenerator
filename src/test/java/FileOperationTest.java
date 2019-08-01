import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOperationTest {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("E:\\IDEAProject\\codeGenerator\\src\\main\\resources\\select.template"));

            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str).append("\r\n");
            }

            String replace = builder.toString().replace("idValue", "selectUserList");

            System.out.println(builder.toString());
            System.out.println(replace);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
