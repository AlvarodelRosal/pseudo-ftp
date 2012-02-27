package alvarodelrosal.ftp.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class FTPUserTest {
    
    @Test
    public void savesTheData() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", false);
        assertEquals("Alvaro", user.getName());
        assertEquals(false, user.isIsAdmin());
        assertEquals(true, user.checkUserAndPassword("adr", "123456"));
    }
    
    @Test
    public void ifUserAndPasswordAreDifferentSaysIt() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", false);
        assertEquals(false, user.checkUserAndPassword("Alvaro", "Alvaro"));
    }
    
    @Test
    public void theUserIsEqualToItselve() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", false);
        assertEquals(true, user.equals(user));
    }
    
    @Test
    public void twoUsersWithTheSameDataAreEquals() {
        FTPUser user1 = new FTPUser("Alvaro", "adr", "123456", false);
        FTPUser user2 = new FTPUser("Alvaro", "adr", "123456", false);
        assertEquals(true, user1.equals(user2));
    }
    
    @Test
    public void twoUsersWithDifferentNameAreNotEquals() {
        FTPUser user1 = new FTPUser("Alvaro", "adr", "123456", false);
        FTPUser user2 = new FTPUser("Paco", "adr", "123456", false);
        assertEquals(false, user1.equals(user2));
    }
    
    @Test
    public void twoUsersWithDifferentUsernameAreNotEquals() {
        FTPUser user1 = new FTPUser("Alvaro", "adr", "123456", false);
        FTPUser user2 = new FTPUser("Alvaro", "Paco", "123456", false);
        assertEquals(false, user1.equals(user2));
    }
    
    @Test
    public void twoUsersWithDifferentPasswordAreNotEquals() {
        FTPUser user1 = new FTPUser("Alvaro", "adr", "123456", false);
        FTPUser user2 = new FTPUser("Alvaro", "adr", "goal", false);
        assertEquals(false, user1.equals(user2));
    }
    
    @Test
    public void twoUsersWithDifferentAdminLevelsAreNotEquals() {
        FTPUser user1 = new FTPUser("Alvaro", "adr", "123456", false);
        FTPUser user2 = new FTPUser("Alvaro", "adr", "123456", true);
        assertEquals(false, user1.equals(user2));
    }
    
}
