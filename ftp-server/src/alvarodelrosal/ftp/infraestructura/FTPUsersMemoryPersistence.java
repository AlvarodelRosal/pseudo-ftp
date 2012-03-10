package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.ArrayList;
import java.util.List;

public class FTPUsersMemoryPersistence implements FTPUsersPersistence{

    private List<FTPUser> users = new ArrayList();
    
    public FTPUsersMemoryPersistence() {
        users.add(new FTPUser("Alvaro", "adr", "123456", true));
        users.add(new FTPUser("Admin", "admin", "admin", true));
        users.add(new FTPUser("User", "user", "user", false));
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
        throw new IllegalArgumentException("The user doesn't exists");
    }

    @Override
    public void addUser(FTPUser user) {
        users.add(user);
    }

    @Override
    public void deleteUser(String name, String username) {
        FTPUser user = selectTheUser(name, username);
        if (user != null) {
            users.remove(user);
        }
    }

    private FTPUser selectTheUser(String name, String username) {
        for(FTPUser user : users) {
            if((user.getName() == null ? name == null : user.getName().equals(name))
                    && (user.getUsername() == null ? username == null : user.getUsername().equals(username))) {
                return user;
            }
        }
        return null;
    }
}
