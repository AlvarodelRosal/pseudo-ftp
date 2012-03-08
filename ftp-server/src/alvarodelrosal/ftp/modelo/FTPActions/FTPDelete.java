package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.util.List;

public class FTPDelete implements FTPAction {

    @Override
    public String getName() {
        return "Delete";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        File path = new File(parameters.get(0));
        if(path.isDirectory()) {
            deleteFolder(path);
        }
        return String.valueOf(path.delete());
    }
    
    public void deleteFolder(File folder) {
        File[] folderContent = folder.listFiles();
        
        for(File file : folderContent) {
            if(file.isDirectory()) {
                deleteFolder(file);
            }
            file.delete();
        }
    }
    
}
