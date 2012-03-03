package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FTPUsersMemoryPersistenceTest {
    
    @Test
    public void addsANewUser() {
        FTPUsersMemoryPersistence users = new FTPUsersMemoryPersistence();
        users.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));
        
        assertEquals(true,users.getAllUsers().contains(
                new FTPUser("alvaro", "alvaro", "alvaro", true)));
    }
    
    @Test
    public void checksIfAUserExists() {
        FTPUsersMemoryPersistence users = new FTPUsersMemoryPersistence();
        users.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));
        
        assertEquals(true,users.existsTheUser("alvaro", "alvaro"));
    }
    
    @Test
    public void ifUserExistsReturnsIt() {
        FTPUsersMemoryPersistence users = new FTPUsersMemoryPersistence();
        users.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));
        
        assertEquals(new FTPUser("alvaro", "alvaro", "alvaro", true),
                users.getTheUser("alvaro", "alvaro"));
    }
    
    @Test
    public void removesAUser() {
        FTPUsersMemoryPersistence users = new FTPUsersMemoryPersistence();
        users.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));
        FTPUser user = users.getTheUser("alvaro", "alvaro");
        users.deleteUser(user);
        
        assertEquals(false,users.getAllUsers().contains(
                new FTPUser("alvaro", "alvaro", "alvaro", true)));
    }
    
    @Test
    public void checkIfAUserDoesNotExists() {
        FTPUsersMemoryPersistence users = new FTPUsersMemoryPersistence();
        users.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));
        FTPUser user = users.getTheUser("alvaro", "alvaro");
        users.deleteUser(user);
        
        assertEquals(false,users.existsTheUser("alvaro", "alvaro"));
    }
}
