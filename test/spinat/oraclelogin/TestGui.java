package spinat.oraclelogin;

import java.awt.Frame;
import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.SQLException;
import oracle.jdbc.OracleConnection;
import java.text.ParseException;

public class TestGui {
    
    @Test
    public void hello() throws Exception {
        SwingUtilities.invokeAndWait
            (new Runnable() {
                    public void run() {
                        new spinat.oraclelogin.OracleLogin("","roland").doLogin();
                    }
                });
    };
}
