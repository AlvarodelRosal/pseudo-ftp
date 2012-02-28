package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.io.PrintWriter;

public class FTPLook implements FTPAction {

    @Override
    public String getName() {
        return "Look";
    }

    @Override
    public void doAction(PrintWriter output, String parameters) {
        StringBuilder pathBuilder = new StringBuilder(parameters);
        
        File path = new File(parameters);
        File[] sonsPath = path.listFiles();
        for (File file : sonsPath) {
            pathBuilder.append("<:@:>");
            pathBuilder.append(file.getName());
        }

        output.println(pathBuilder.toString());
    }
}
