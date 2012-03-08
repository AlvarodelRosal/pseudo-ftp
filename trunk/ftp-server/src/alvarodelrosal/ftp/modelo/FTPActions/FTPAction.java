package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.List;

public interface FTPAction {
    
    public String getName();
    public boolean needsAdminPrivileges();
    public String doAction(List<String> parameters);
    
}
