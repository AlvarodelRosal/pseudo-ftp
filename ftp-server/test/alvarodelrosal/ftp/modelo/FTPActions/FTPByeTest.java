package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class FTPByeTest {
    
    FTPBye bye = new FTPBye();
    
    @Test
    public void givesTheRightName() {
        assertEquals("Bye", bye.getName());
    }
    
    @Test
    public void givesTheRightAdminPrivileges() {
        assertEquals(false, bye.needsAdminPrivileges());
    }
    
    @Test
    public void returnTheRightAnwser() {
        assertEquals("Enjoy", bye.doAction(new ArrayList()));
    }
}
