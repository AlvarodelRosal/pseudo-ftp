package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class FTPOopsTest {
    
    FTPOops oops = new FTPOops();
    
    @Test
    public void givesTheRightName() {
        assertEquals("Oops", oops.getName());
    }
    
    @Test
    public void givesTheRightAdminPrivileges() {
        assertEquals(false, oops.needsAdminPrivileges());
    }
    
    @Test
    public void returnTheRightAnwser() {
        assertEquals("Oops... action not found.", oops.doAction(new ArrayList()));
    }
    
}
