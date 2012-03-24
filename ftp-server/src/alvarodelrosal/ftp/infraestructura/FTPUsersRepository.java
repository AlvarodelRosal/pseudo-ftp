package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.List;

public class FTPUsersRepository {

    private String file;
    private FTPUsersFilePersistence users;

    public FTPUsersRepository(String file) {
        this.file = file;
        this.users = new FTPUsersFilePersistence(file);
    }
    
    public boolean exists(String username, String password) {
        return users.existsTheUser(username, password);
    }
    
    public FTPUser getUser(String username, String password) {
        return users.getTheUser(username, password);
    }
    
    public void addUser(FTPUser user) {
        users.addUser(user);
    }
    
    public void deleteUser(String name, String username) {
        users.deleteUser(name, username);
    }
    
    public List<FTPUser> getAllUsers() {
        return users.getAllUsers();
    }
    
    public void update() {
        users = null;
        users = new FTPUsersFilePersistence(file);
    }
    
}
