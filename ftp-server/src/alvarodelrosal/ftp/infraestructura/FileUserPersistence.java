package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.List;

public class FileUserPersistence implements Persistence {

    @Override
    public List<FTPUser> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existsTheUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FTPUser getTheUser(String user, String Password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addUser(FTPUser user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUser(FTPUser user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
