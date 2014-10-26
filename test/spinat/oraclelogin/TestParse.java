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
        OraConnectionDesc d =  OraConnectionDesc.fromString("a@n");
        assertTrue(!d.hasPwd());
        
        OraConnectionDesc d2 =  OraConnectionDesc.fromString("a/b@n");
        assertTrue(d2.hasPwd());
        
        OraConnectionDesc d3 =  OraConnectionDesc.fromString("a/b@n:12:roland");
        assertTrue(d3.hasPwd());
        
        OraConnectionDesc d4 = OraConnectionDesc.fromString("a@n:12:roland");
        assertTrue(!d4.hasPwd());
    }

    @Test(expected=ParseException.class) 
    public void test2() throws ParseException {
        OraConnectionDesc d4 =  OraConnectionDesc.fromString("a@n:12i:roland");
        //assertTrue(!d4.hasPwd());
    }
    @Test(expected=ParseException.class) 
    public void test3() throws ParseException {
        OraConnectionDesc d1 =  
            OraConnectionDesc.fromString("a@n:12:roland:a:x:g");
    }
}
