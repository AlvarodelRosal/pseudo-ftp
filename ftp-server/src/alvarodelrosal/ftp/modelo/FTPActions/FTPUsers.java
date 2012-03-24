package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPUser;
import java.util.List;

public class FTPUsers implements FTPAction {

    private String file;

    public FTPUsers(String file) {
        this.file = file;
    }
    
    @Override
    public String getName() {
        return "Users";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return true;
    }

    @Override
    public String doAction(List<String> parameters) {
        StringBuilder usersBuilder = new StringBuilder();
        List<FTPUser> users = new FTPUsersRepository(file).getAllUsers();
        
        for(FTPUser user : users) {
            usersBuilder.append("<:@:>");
            usersBuilder.append(user.getName());
            usersBuilder.append("<:@:>");
            usersBuilder.append(user.getUsername());
            usersBuilder.append("<:@:>");
            usersBuilder.append(user.isAdmin());
        }
        
        return usersBuilder.toString().substring(5);
    }
}
