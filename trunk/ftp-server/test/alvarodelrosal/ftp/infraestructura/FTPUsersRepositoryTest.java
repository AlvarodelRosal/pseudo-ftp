package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FTPUsersRepositoryTest {

    @Test
    public void aUserAddedCanBeFound() {
        FTPUsersRepository repository = new FTPUsersRepository();
        repository.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));

        assertEquals(true, repository.exists("alvaro", "alvaro"));
    }

    @Test
    public void aUserAddedCanBeRecovered() {
        FTPUsersRepository repository = new FTPUsersRepository();
        repository.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));

        assertEquals(new FTPUser("alvaro", "alvaro", "alvaro", true),
                repository.getUser("alvaro", "alvaro"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void aUserRemovedCanNotBeRecovered() {
        FTPUsersRepository repository = new FTPUsersRepository();
        repository.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));
        repository.deleteUser(repository.getUser("alvaro", "alvaro"));
        
        repository.getUser("alvaro", "alvaro");
    }
    
    @Test
    public void aUserRemovedCanNotBeFound() {
        FTPUsersRepository repository = new FTPUsersRepository();
        repository.addUser(new FTPUser("alvaro", "alvaro", "alvaro", true));
        repository.deleteUser(repository.getUser("alvaro", "alvaro"));
        
        assertEquals(false, repository.exists("alvaro", "alvaro"));
    }
}
