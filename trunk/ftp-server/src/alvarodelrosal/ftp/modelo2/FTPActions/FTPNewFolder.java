package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.util.List;

public class FTPNewFolder implements FTPAction {

    @Override
    public String getName() {
        return "NewFolder";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return true;
    }

    @Override
    public String doAction(List<String> parameters) {
        File path = new File(parameters.get(0));
        return String.valueOf(path.mkdir());
    }
    
}
