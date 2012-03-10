package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import java.util.List;

public class FTPDeleteUser implements FTPAction {
    
    private FTPUsersRepository ftpUsersRepository = new FTPUsersRepository();

    public FTPDeleteUser() {
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
