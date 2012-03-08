package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.List;

public class FTPOops implements FTPAction{

    @Override
    public String getName() {
        return "Oops";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        return "Oops... action not found.";
    }
    
}
