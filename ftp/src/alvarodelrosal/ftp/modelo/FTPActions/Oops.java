package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.PrintWriter;

public class Oops implements FTPAction {

    @Override
    public String getName() {
        return "No Exists";
    }

    @Override
    public void doAction(PrintWriter output, String parameters) {
        output.println("Oops... command not found");
    }

}
