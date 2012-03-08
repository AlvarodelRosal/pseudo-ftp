package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.List;

public class FTPLogin implements FTPAction {

    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        return parameters.get(0) + "<:@:>" + parameters.get(1);
    }

}
