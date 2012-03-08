package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.List;

public class FTPLoginError implements FTPAction{

    @Override
    public String getName() {
        return "loginError";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        return "Oops... login failled";
    }
    
}