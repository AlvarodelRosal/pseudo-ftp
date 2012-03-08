package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.modelo.FTPConnection;
import java.util.List;

public class FTPHello implements FTPAction {

    @Override
    public String getName() {
        return "Hello";
    }
    
    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }
    
    @Override
    public String doAction(List<String> parameters) {
        if(parameters.contains(FTPConnection.TOKEN)) {
            throw new IllegalArgumentException("The token can't be a parameter");
        }
        
        StringBuilder answer = new StringBuilder("Eh... What's up, doc?");
        for (String parameter : parameters) {
            answer.append("<:@:>");
            answer.append(parameter);
        }
        return answer.toString();
    }

}
