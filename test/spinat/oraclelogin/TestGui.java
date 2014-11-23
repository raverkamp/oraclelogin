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
import javax.swing.JOptionPane;

public class TestGui {
    
    @Test
    public void test1() throws Exception {
        SwingUtilities.invokeAndWait
            (new Runnable() {
                    public void run() {
                        new spinat.oraclelogin.OracleLogin("","roland").doLogin();
                    }
                });
    };
    
    @Test
    public void test2() throws Exception {
        SwingUtilities.invokeAndWait
            (new Runnable() {
                    public void run() {
                        OracleLogin olo = new spinat.oraclelogin.OracleLogin("","roland");
                        OracleLogin.OracleLoginResult res =   olo.doLogin();
                        JOptionPane.showMessageDialog(null, "This is an intermediate message: no reset");
                        OracleLogin.OracleLoginResult res2 =   olo.doLogin();
                    }
                });
    };
    
     @Test
    public void test3() throws Exception {
        SwingUtilities.invokeAndWait
            (new Runnable() {
                    public void run() {
                        OracleLogin olo = new spinat.oraclelogin.OracleLogin("","roland");
                        OracleLogin.OracleLoginResult res =   olo.doLogin();
                        olo.reset();
                        JOptionPane.showMessageDialog(null, "This is an intermediate message: reset");
                        OracleLogin.OracleLoginResult res2 = olo.doLogin();
                    }
                });
    };
    
    @Test
    public void test4() throws Exception {
        SwingUtilities.invokeAndWait
            (new Runnable() {
                    public void run() {
                        OracleLogin olo = new spinat.oraclelogin.OracleLogin("","roland",new ConnectionCheck() {
                            @Override
                            public String check(OraConnectionDesc desc, OracleConnection con) {
                                return "Does not work";
                            }
                        });
                         OracleLogin.OracleLoginResult res =   olo.doLogin();
                    }
                });
    };
    
     @Test
    public void test5() throws Exception {
        SwingUtilities.invokeAndWait
            (new Runnable() {
                    public void run() {
                        OracleLogin olo = new spinat.oraclelogin.OracleLogin("","roland",new ConnectionCheck() {
                            @Override
                            public String check(OraConnectionDesc desc, OracleConnection con) {
                                return null;
                            }
                        });
                         OracleLogin.OracleLoginResult res =   olo.doLogin();
                    }
                });
    };
    
    
}
