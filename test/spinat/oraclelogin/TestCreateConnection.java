package spinat.oraclelogin;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roland
 */
public class TestCreateConnection {

    final private ArrayList<String> cons;

    public TestCreateConnection() throws IOException {
        Properties p = new Properties();
        FileInputStream in = new FileInputStream("testprops.txt");
        p.load(in);
        cons = new ArrayList<>();
        for (Enumeration<String> e = (Enumeration<String>) p.propertyNames(); e.hasMoreElements();) {
            String name = e.nextElement();
            if (name.startsWith("con")) {
                cons.add(p.getProperty(name));
            }
        }
    }

    @Test
    public void Test1() throws ParseException, SQLException {
        for (String con : cons) {
            OraConnectionDesc d = OraConnectionDesc.fromString(con);
            System.out.println(con+ " -> " + d.display());
            try (OracleConnection c = d.getConnection()) {
                try (Statement stm = c.createStatement();
                        ResultSet rs = stm.executeQuery("select dummy from dual")) {
                    rs.next();
                    System.out.println(rs.getString(1));
                }
            }
        }
    }
}
