package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.List;

public class FTPAddUser implements FTPAction {
    
    private FTPUsersRepository ftpUsersRepository = new FTPUsersRepository();

    public FTPAddUser() {
    }

    @Override
    public String getName() {
        return "AddUser";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return true;
    }

    @Override
    public String doAction(List<String> parameters) {
        FTPUser user = new FTPUser(parameters.get(0), parameters.get(1),
                parameters.get(2), Boolean.parseBoolean(parameters.get(3)));
        ftpUsersRepository.addUser(user);
        return " ";
    }

}
