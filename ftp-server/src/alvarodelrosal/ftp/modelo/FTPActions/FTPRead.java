package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
        int part = Integer.parseInt(parameters.get(1));
        FileInputStream fileReader = null;
        
        try {
            fileReader = new FileInputStream(new File(path));
        } catch (FileNotFoundException ex) {
            return "File does not exists";
        }

        StringBuilder fileBuilder = new StringBuilder();
        byte[] filePart = new byte[1000];

        try {
            fileReader.read(filePart, 1000 * part, 999);
        } catch (IOException ex) {
        }

        for (byte fileByte : filePart) {
            fileBuilder.append(fileByte);
        }

        try {
            fileReader.close();
        } catch (IOException ex) {
        }

        return fileBuilder.toString();
    }
}
