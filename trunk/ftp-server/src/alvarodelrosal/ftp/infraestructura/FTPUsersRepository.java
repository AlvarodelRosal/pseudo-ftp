package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;

public class FTPUsersRepository {
    
    FTPUsersMemoryPersistence users = new FTPUsersMemoryPersistence();
    
    public boolean exists(String username, String password) {
        return users.existsTheUser(username, password);
    }
    
    public FTPUser getUser(String username, String password) {
        return users.getTheUser(username, password);
    }
    
    public void addUser(FTPUser user) {
        users.addUser(user);
    }
    
    public void deleteUser(FTPUser user) {
        users.deleteUser(user);
    }
    
}
