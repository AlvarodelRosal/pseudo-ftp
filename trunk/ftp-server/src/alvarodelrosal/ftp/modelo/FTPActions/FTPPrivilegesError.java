package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.List;

public class FTPPrivilegesError implements FTPAction{

    @Override
    public String getName() {
        return "privilegesError";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        return "To do this, you should be an Admin";
    }
    
}
