package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FTPUsers implements FTPAction {

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
        List<FTPUser> users = new ArrayList();
        
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
