package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FTPPrivilegesErrorTest {
    
    FTPPrivilegesError error = new FTPPrivilegesError();
    
    @Test
    public void givesTheRightName() {
        assertEquals("privilegesError", error.getName());
    }
    
    @Test
    public void givesTheRightAdminPrivileges() {
        assertEquals(false, error.needsAdminPrivileges());
    }
    
    @Test
    public void returnTheRightAnwser() {
        assertEquals("To do this, you should be an Admin",
                error.doAction(new ArrayList()));
    }
}
