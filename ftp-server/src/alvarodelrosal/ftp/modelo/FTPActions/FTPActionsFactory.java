package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import java.util.List;

public class FTPActionsFactory {
    
    List<FTPAction> actions = new ArrayList();
    
    public FTPActionsFactory() {
        addsAllTheActions();
    }
    
    public FTPAction getFTPAction(String actionName) {
        for(FTPAction action : actions) {
            if(action.getName() == null ? actionName == null : action.getName().equals(actionName)) {
                return action;
            }
        }
        return new FTPOops();
    }
    
    private void addsAllTheActions() {
        actions.add(new FTPHello());
        actions.add(new FTPLook());
        actions.add(new FTPCanRead());
        actions.add(new FTPCanWrite());
        actions.add(new FTPIsHidden());
        actions.add(new FTPLastModified());
        actions.add(new FTPSize());
        actions.add(new FTPIsFolder());
        actions.add(new FTPIsFile());
        actions.add(new FTPNewFolder());
    }
}
