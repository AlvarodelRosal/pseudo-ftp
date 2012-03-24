package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import java.util.List;

public class FTPDeleteUser implements FTPAction {
    
    private FTPUsersRepository ftpUsersRepository;
    private String file;

    public FTPDeleteUser(String file) {
        this.file = file;
        ftpUsersRepository = new FTPUsersRepository(file);
    }

    @Override
    public String getName() {
        return "DeleteUser";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return true;
    }

    @Override
    public String doAction(List<String> parameters) {
        ftpUsersRepository.update();
        ftpUsersRepository.deleteUser(parameters.get(0), parameters.get(1));
        ftpUsersRepository.update();
        return " ";
    }

}
