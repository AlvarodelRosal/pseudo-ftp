package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.modelo.FTPActions.FTPAction;
import java.util.List;

public class FTPBye implements FTPAction{

    @Override
    public String getName() {
        return "Bye";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        return "Enjoy";
    }
    
}
