package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPWrite implements FTPAction {

    @Override
    public String getName() {
        return "Write";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return true;
    }

    @Override
    public String doAction(List<String> parameters) {
        String path = parameters.get(0);
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
            Logger.getLogger(FTPWrite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fileReader != null) {fileReader.close();}
            } catch (IOException ex) {
            }
        }
        return "";
    }
}
