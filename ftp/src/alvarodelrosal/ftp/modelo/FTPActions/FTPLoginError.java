package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.PrintWriter;

public class FTPLoginError implements FTPAction{
    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public void doAction(PrintWriter output, String parameters) {
        output.println("Oops... login fail.");
    }
}
