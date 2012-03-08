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

        StringBuilder pathBuilder = new StringBuilder();
        if (path.isDirectory()) {
            String[] content = path.list();
            if (content == null) {
                return " ";
            } else {
                for (String subpath : content) {
                    pathBuilder.append("<:@:>");
                    pathBuilder.append(subpath);
                }
            }
            String pathTarget = pathBuilder.toString();
            if (pathTarget.startsWith("<:@:>")) {
                pathTarget = pathTarget.substring(5);
            }
            return pathTarget;
        } else {
            return "This is not a folder";
        }

    }
}
