package spinat.oraclelogin;

import java.sql.SQLException;
import oracle.jdbc.OracleConnection;

public abstract class OraConnectionDesc {

    protected String user;
    protected String pwd;

    public abstract String display();
    
    public boolean hasPwd() {
       return pwd != null;
    }
    
    // or functional record update
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public abstract OracleConnection getConnection() throws SQLException;
    // ensure the driver is loaded
    static final oracle.jdbc.driver.OracleDriver d = new oracle.jdbc.driver.OracleDriver();

    public static OraConnectionDesc fromString(String conStr) {
        final int p = conStr.indexOf("@");
        if (p <= 0) {
            throw new RuntimeException("expecting a conenction string in the form \"user[/pwd]@tnsname\" or \"user[/pwd]@host:port:service\"  ");
        }
        final String userPart = conStr.substring(0, p);
        final String rest = conStr.substring(p + 1);
        final int p2 = userPart.indexOf("/");
        final String user;
        final String pwd;
        if (p2 <= 0) {
            user = userPart;
            pwd = null;
        } else {
            user = userPart.substring(0, p2);
            pwd = conStr.substring(p2 + 1);
        }

        final int pcolon1 = rest.indexOf(":");
        if (pcolon1 < 0) {
            return new OciConnectionDesc(user, pwd, rest);
        } else {
            final int pcolon2 = rest.indexOf(":", pcolon1 + 1);
            if (pcolon2 >= 0) {
                final String[] a = rest.split(":");
                if (a.length != 3) {
                    throw new RuntimeException("expecting more");
                }
                final int x = Integer.parseInt(a[1]);
                return new ThinConnectionDesc(user, pwd, a[0], x, a[2]);
            } else {
                throw new RuntimeException("expecting a connection string in the form \"user/pwd@host:port:service\"");
            }
        }
    }
}
