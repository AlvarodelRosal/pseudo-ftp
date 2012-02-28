package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.PrintWriter;

public class FTPLoginSuccess implements FTPAction{
    @Override
    public String getName() {
        return "LoginSuccess";
    }

    @Override
    public void doAction(PrintWriter output, String parameters) {
        output.println("Welcome!");
    }
}
