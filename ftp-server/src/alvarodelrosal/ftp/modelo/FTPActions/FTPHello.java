package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.PrintWriter;

public class FTPHello implements FTPAction{

    @Override
    public String getName() {
        return "Hello";
    }

    @Override
    public void doAction(PrintWriter output, String parameters) {
        output.println("Eh... What's up, doc?<:@:>" + parameters);
    }

}
