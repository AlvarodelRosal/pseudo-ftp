package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import java.util.List;

public class FTPActionsFactory {
    
    private List<FTPAction> actions = new ArrayList();
    
    public FTPActionsFactory(String file, String inbox) {
        addsAllTheActions(file, inbox);
    }
    
    public FTPAction getFTPAction(String actionName) {
        for(FTPAction action : actions) {
            if(action.getName() == null ? actionName == null : action.getName().equals(actionName)) {
                return action;
            }
        }
        return new FTPOops();
    }
    
    private void addsAllTheActions(String file, String inbox) {
        actions.add(new FTPAddUser(file));
        actions.add(new FTPCanRead());
        actions.add(new FTPCanWrite());
        actions.add(new FTPDelete());
        actions.add(new FTPDeleteUser(file));
        actions.add(new FTPHello());
        actions.add(new FTPIsFile());
        actions.add(new FTPIsFolder());
        actions.add(new FTPIsHidden());
        actions.add(new FTPLastModified());
        actions.add(new FTPLook());
        actions.add(new FTPNewFolder());
        actions.add(new FTPNotAdminWrite(inbox));
        actions.add(new FTPRead());
        actions.add(new FTPSize());
        actions.add(new FTPUsers(file));
        actions.add(new FTPWrite());
    }
}
