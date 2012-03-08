package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.util.List;

public class FTPIsFile implements FTPAction {

    @Override
    public String getName() {
        return "IsFile";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        File path = new File(parameters.get(0));
        return String.valueOf(path.isFile());
    }
    
}
