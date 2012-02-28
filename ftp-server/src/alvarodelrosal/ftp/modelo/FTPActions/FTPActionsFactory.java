package alvarodelrosal.ftp.modelo.FTPActions;

import java.util.ArrayList;
import java.util.List;

public class FTPActionsFactory {

    private List<FTPAction> actions = new ArrayList();

    public FTPActionsFactory() {
        actions.add(new FTPHello());
        actions.add(new FTPLook());
    }
    
    public FTPAction get(String name) {
        for(FTPAction action : actions) {
            if(action.getName().equals(name)) {
                return action;
            }
        }
        return new FTPOops();
    }
    
}
