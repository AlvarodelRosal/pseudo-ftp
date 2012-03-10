package alvarodelrosal.ftp.infraestructura;

import alvarodelrosal.ftp.modelo.FTPUser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FTPUsersFilePersistence implements FTPUsersPersistence {

    private List<FTPUser> users = new ArrayList();

    public FTPUsersFilePersistence() {
        BufferedReader reader = null;
        try {
            File usersFile = new File("users.txt");
            reader = new BufferedReader(new FileReader(usersFile));
            String line;
            while ((line = reader.readLine()) != null) {
                buildUser(line);
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(FTPUsersFilePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(FTPUsersFilePersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public List<FTPUser> getAllUsers() {
        return users;
    }

    @Override
    public boolean existsTheUser(String username, String password) {
        for (FTPUser user : users) {
            if (user.is(username, password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public FTPUser getTheUser(String username, String password) {
        for (FTPUser user : users) {
            if (user.is(username, password)) {
                return user;
            }
        }
        throw new IllegalArgumentException("The user doesn't exists");
    }

    @Override
    public void addUser(FTPUser user) {
        if (!userExists(user)) {
            users.add(user);
            writeUsers();
        }
    }

    @Override
    public void deleteUser(String name, String username) {
        FTPUser user = selectTheUser(name, username);
        if (user != null) {
            users.remove(user);
            writeUsers();
        }
    }

    private FTPUser selectTheUser(String name, String username) {
        for (FTPUser user : users) {
            if ((user.getName() == null ? name == null : user.getName().equals(name))
                    && (user.getUsername() == null ? username == null : user.getUsername().equals(username))) {
                return user;
            }
        }
        return null;
    }

    private void buildUser(String line) {
        String[] parts = line.split("<:@:>");
        FTPUser user = new FTPUser(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
        users.add(user);
    }

    private void writeUsers() {
        File file = new File("users.txt");
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            file.delete();
            file.createNewFile();
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (FTPUser user : users) {
                bufferedWriter.write(user.getName() + "<:@:>" + user.getUsername()
                        + "<:@:>" + user.getPassword() + "<:@:>" + user.isAdmin() + "\n");
            }
        } catch (IOException ex) {
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(FTPUsersFilePersistence.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException ex) {
                            Logger.getLogger(FTPUsersFilePersistence.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }
        }
    }

    private boolean userExists(FTPUser user) {
        for(FTPUser userSelected : users) {
            if(userSelected.getUsername() == null ? user.getUsername() == null : userSelected.getUsername().equals(user.getUsername())) {
                return true;
            } 
        }
        return false;
    }
}