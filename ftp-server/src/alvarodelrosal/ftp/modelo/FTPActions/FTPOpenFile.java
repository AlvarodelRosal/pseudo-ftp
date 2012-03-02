package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPOpenFile implements FTPAction {

    @Override
    public String getName() {
        return "Open";
    }

    @Override
    public void doAction(PrintWriter output, String parameters) {
        String fileName = parameters;
        try {
            FileInputStream fileReader = new FileInputStream(new File(fileName));
            fileReader.
            
        } catch (FileNotFoundException ex) {
            output.println("File " + fileName + " not found");
        }
        
        
    }
    
}
