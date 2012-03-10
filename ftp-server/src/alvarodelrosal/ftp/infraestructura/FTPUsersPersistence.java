package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.List;

public interface FTPUsersPersistence {
    
    public List<FTPUser> getAllUsers();
    public boolean existsTheUser(String username, String password);
    public FTPUser getTheUser(String username, String password);
    public void addUser(FTPUser user);
    public void deleteUser(String name, String username);
    
}
