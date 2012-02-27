package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.List;

public interface Persistence {
    
    public List<FTPUser> getAllUsers();
    public boolean existsTheUser(String username, String password);
    public FTPUser getTheUser(String username, String Password);
    public void addUser(FTPUser user);
    public void deleteUser(FTPUser user);
    
}
