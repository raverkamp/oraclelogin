package spinat.oraclelogin;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spinat.oraclelogin.*;
import java.text.ParseException;

public class TestParse {

    public TestParse() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test1() throws ParseException {
        {
            OraConnectionDesc d = OraConnectionDesc.fromString("a@n");
            assertTrue(!d.hasPwd());
            assertTrue(d instanceof OciConnectionDesc);
            OciConnectionDesc od = (OciConnectionDesc) d;
            assertEquals("n", od.tnsname);
        }

        {
            OraConnectionDesc d = OraConnectionDesc.fromString("a/b@n");
            assertTrue(d.hasPwd());
            assertTrue(d.getPassword().equals("b"));
            assertTrue(d.getUser().equals("a"));
            assertTrue(d instanceof OciConnectionDesc);
            OciConnectionDesc od = (OciConnectionDesc) d;
            assertEquals("n", od.tnsname);
        }
        {
            OraConnectionDesc d = OraConnectionDesc.fromString("a/b@n:12:roland");
            assertTrue(d.hasPwd());
            assertTrue(d.getPassword().equals("b"));
            assertTrue(d.getUser().equals("a"));
            assertTrue(d instanceof ThinConnectionDesc);
            ThinConnectionDesc tc = (ThinConnectionDesc) d;
            assertEquals("n", tc.host);
            assertEquals(12, tc.port);
            assertEquals("roland", tc.service);
        }
        {
            OraConnectionDesc d = OraConnectionDesc.fromString("a@n:12:roland");
            assertTrue(!d.hasPwd());
            assertTrue(d.getUser().equals("a"));
            assertTrue(d instanceof ThinConnectionDesc);
            ThinConnectionDesc tc = (ThinConnectionDesc) d;
            assertEquals("n", tc.host);
            assertEquals(12, tc.port);
            assertEquals("roland", tc.service);
        }
        {
            OraConnectionDesc d = OraConnectionDesc.fromString("a/b@//n:12/roland");
            assertTrue(d.hasPwd());
            assertEquals("b", d.getPassword());
            assertTrue(d.getUser().equals("a"));
            assertTrue(d instanceof ThinConnectionDesc);
            ThinConnectionDesc tc = (ThinConnectionDesc) d;
            assertEquals("n", tc.host);
            assertEquals(12, tc.port);
            assertEquals("roland", tc.service);
        }
        {
            OraConnectionDesc d = OraConnectionDesc.fromString("a@//n:12/roland");
            assertTrue(!d.hasPwd());
            assertTrue(d.getUser().equals("a"));
            assertTrue(d instanceof ThinConnectionDesc);
            ThinConnectionDesc tc = (ThinConnectionDesc) d;
            assertEquals("n", tc.host);
            assertEquals(12, tc.port);
            assertEquals("roland", tc.service);
        }
    }

    @Test(expected = ParseException.class)
    public void test2() throws ParseException {
        OraConnectionDesc d = OraConnectionDesc.fromString("a@n:12i:roland");
        //assertTrue(!d4.hasPwd());
    }

    @Test(expected = ParseException.class)
    public void test3() throws ParseException {
        OraConnectionDesc d
                = OraConnectionDesc.fromString("a@n:12:roland:a:x:g");
    }

    @Test(expected = ParseException.class)
    public void test4() throws ParseException {
        OraConnectionDesc d
                = OraConnectionDesc.fromString("a@//n:12:roland");
    }

    @Test(expected = ParseException.class)
    public void test5() throws ParseException {
        OraConnectionDesc d
                = OraConnectionDesc.fromString("a@//n/12:roland");
    }
}
