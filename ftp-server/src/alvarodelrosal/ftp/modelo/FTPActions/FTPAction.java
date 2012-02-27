package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.PrintWriter;

public interface FTPAction {
    
    public String getName();
    public void doAction(PrintWriter output, String parameters);
    
}
