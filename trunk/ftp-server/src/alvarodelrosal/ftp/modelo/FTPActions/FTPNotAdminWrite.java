package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPNotAdminWrite implements FTPAction {

    private String inbox;

    public FTPNotAdminWrite(String inbox) {
        this.inbox = inbox;
    }
    
    @Override
    public String getName() {
        return "NotAdminWrite";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        String path = inbox + "/" + parameters.get(0);
        parameters.remove(0);
        
        FileInputStream fileReader = null;

        try {
            File file = new File(path);
            file.createNewFile();
            FileOutputStream writer = new FileOutputStream(file);

            for (String readedByte : parameters) {
                writer.write(Integer.parseInt(readedByte));
            }
            return "";
        } catch (IOException ex) {
            Logger.getLogger(FTPNotAdminWrite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fileReader != null) {fileReader.close();}
            } catch (IOException ex) {
            }
        }
        return "";
    }
}
