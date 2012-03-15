package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPRead implements FTPAction {
    
    @Override
    public String getName() {
        return "Read";
    }
    
    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }
    
    @Override
    public String doAction(List<String> parameters) {
        String path = parameters.get(0);
        
        FileInputStream fileReader = null;
        
        try {
            fileReader = new FileInputStream(new File(path));
            StringBuilder fileBuilder = new StringBuilder();
            
            int filePart;
            
            while ((filePart = fileReader.read()) != -1) {
                fileBuilder.append("<:@:>");
                fileBuilder.append(filePart);
            }
            return fileBuilder.toString().substring(5);
        } catch (IOException ex) {
            Logger.getLogger(FTPRead.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
            }
        }
        return "";
    }
}
