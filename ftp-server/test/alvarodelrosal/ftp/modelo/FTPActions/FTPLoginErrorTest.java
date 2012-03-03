package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class FTPLoginErrorTest {
    
    FTPLoginError loginError = new FTPLoginError();
    
    @Test
    public void givesTheRightName() {
        assertEquals("loginError", loginError.getName());
    }
    
    @Test
    public void givesTheRightAdminPrivileges() {
        assertEquals(false, loginError.needsAdminPrivileges());
    }
    
    @Test
    public void returnTheRightAnwser() {
        assertEquals("Oops... login failled", loginError.doAction(new ArrayList()));
    }
    
}
