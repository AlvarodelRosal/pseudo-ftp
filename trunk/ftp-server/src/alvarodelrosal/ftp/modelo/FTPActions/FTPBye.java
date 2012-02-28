package alvarodelrosal.ftp.modelo.FTPActions;

import alvarodelrosal.ftp.modelo.FTPActions.FTPAction;
import java.io.PrintWriter;

public class FTPBye implements FTPAction{

    @Override
    public String getName() {
        return "Bye";
    }

    @Override
    public void doAction(PrintWriter output, String parameters) {
        output.println("bye");
    }
    
}
