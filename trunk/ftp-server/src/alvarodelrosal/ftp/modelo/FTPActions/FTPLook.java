package alvarodelrosal.ftp.modelo.FTPActions;

import java.io.File;
import java.util.List;

public class FTPLook implements FTPAction {

    @Override
    public String getName() {
        return "Look";
    }

    @Override
    public boolean needsAdminPrivileges() {
        return false;
    }

    @Override
    public String doAction(List<String> parameters) {
        String pathName = parameters.get(0);
        File path = new File(pathName);

        StringBuilder pathBuilder = new StringBuilder(path.getAbsolutePath());
        if (path.isDirectory()) {
            String[] content = path.list();
            
            for(String subpath : content) {
                pathBuilder.append("<:@:>");
                pathBuilder.append(subpath);
            }
            return pathBuilder.toString().substring(5);
        } else {
            return "This is not a folder";
        }
        
    }
    
}
