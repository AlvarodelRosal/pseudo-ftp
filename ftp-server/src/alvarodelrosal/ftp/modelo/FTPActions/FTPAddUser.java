package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.List;

public class FTPAddUser implements FTPAction {
    
    private FTPUsersRepository ftpUsersRepository;
    private String file;

    public FTPAddUser(String file) {
        this.file = file;
        ftpUsersRepository = new FTPUsersRepository(file);
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
        ftpUsersRepository.update();
        FTPUser user = new FTPUser(parameters.get(0), parameters.get(1),
                parameters.get(2), Boolean.parseBoolean(parameters.get(3)));
        ftpUsersRepository.addUser(user);
        ftpUsersRepository.update();
        return " ";
    }

}
