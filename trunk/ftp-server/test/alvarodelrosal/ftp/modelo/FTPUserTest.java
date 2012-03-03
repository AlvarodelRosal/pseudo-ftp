package alvarodelrosal.ftp.modelo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FTPUserTest {
    
    @Test
    public void savesTheCorrectData() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        assertEquals("Alvaro", user.getName());
        assertEquals(true, user.is("adr", "123456"));
        assertEquals(true, user.isAdmin());
    }
    
    @Test
    public void ifUsernameIsIncorrectReturnsALoginError() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        assertEquals(false, user.is("pepe", "123456"));
    }
    
    @Test
    public void ifPasswordIsIncorrectReturnsALoginError() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        assertEquals(false, user.is("adr", "qwerty"));
    }
    
    @Test
    public void ifBothAreIncorrectReturnsALoginError() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        assertEquals(false, user.is("pepe", "qwerty"));
    }
    
    @Test
    public void twoUsersWithTheSameDataAreEquals() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        FTPUser user2 = new FTPUser("Alvaro", "adr", "123456", true);
        assertEquals(true, user.equals(user2));
    }
    
    @Test
    public void twoUsersWithDiferentNameAreNotEquals() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        FTPUser user2 = new FTPUser("Pepe", "adr", "123456", true);
        assertEquals(false, user.equals(user2));
    }
    
    @Test
    public void twoUsersWithDiferentUsernameNotEquals() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        FTPUser user2 = new FTPUser("Alvaro", "alvaro", "123456", true);
        assertEquals(false, user.equals(user2));
    }
    
    @Test
    public void twoUsersWithDiferentPasswordNotEquals() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        FTPUser user2 = new FTPUser("Alvaro", "adr", "qwerty", true);
        assertEquals(false, user.equals(user2));
    }
    
    @Test
    public void twoUsersWithDiferentUserLevelAreNotEquals() {
        FTPUser user = new FTPUser("Alvaro", "adr", "123456", true);
        FTPUser user2 = new FTPUser("Alvaro", "adr", "123456", false);
        assertEquals(false, user.equals(user2));
    }
    
}
