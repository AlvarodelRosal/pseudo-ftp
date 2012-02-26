package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.ArrayList;
import java.util.List;

public class MemoryUserPersistence implements Persistence {

    private List<FTPUser> users = new ArrayList();
    
    public MemoryUserPersistence() {
        users.add(new FTPUser("Alvaro", "adr", "123456", true));
    }
    
    @Override
    public List<FTPUser> getAllUsers() {
        return users;
    }

    @Override
    public boolean existsTheUser(String username, String password) {
        for (FTPUser user : users) {
            if (user.checkUserAndPassword(username, password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public FTPUser getTheUser(String username, String password) {
        for (FTPUser user : users) {
            if (user.checkUserAndPassword(username, password)) {
                return user;
            }
        }
        throw new RuntimeException("The user does not exists");
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
