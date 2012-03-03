package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.ArrayList;
import java.util.List;

public class FTPUsersMemoryPersistence implements FTPUsersPersistence{

    private List<FTPUser> users = new ArrayList();
    
    public FTPUsersMemoryPersistence() {
        users.add(new FTPUser("Alvaro", "adr", "123456", true));
        users.add(new FTPUser("Admin", "admin", "admin", true));
        users.add(new FTPUser("User", "user", "user", true));
    }

    @Override
    public List<FTPUser> getAllUsers() {
        return users;
    }

    @Override
    public boolean existsTheUser(String username, String password) {
        for(FTPUser user : users) {
            if(user.is(username, password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public FTPUser getTheUser(String username, String password) {
        for(FTPUser user : users) {
            if(user.is(username, password)) {
                return user;
            }
        }
        throw new IllegalArgumentException("the user doesn't existis");
    }

    @Override
    public void addUser(FTPUser user) {
        users.add(user);
    }

    @Override
    public void deleteUser(FTPUser user) {
        users.remove(user);
    }
    
}
