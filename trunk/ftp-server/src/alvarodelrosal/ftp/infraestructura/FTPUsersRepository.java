package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;

public class FTPUsersRepository {
    
    MemoryUserPersistence users = new MemoryUserPersistence();

    public FTPUser get(String username, String password) {
        if(users.existsTheUser(username, password)) {
            return users.getTheUser(username, password);
        } else {
            return null;
        }
    }
    
    public void add(FTPUser user) {
        users.addUser(user);
    }
    
    public void delete(FTPUser user) {
        users.deleteUser(user);
    }
    
    public boolean exists (String username, String password) {
        return users.existsTheUser(username, password);
    }
    
}
