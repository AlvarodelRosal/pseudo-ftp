package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.modelo.FTPActions.FTPLoginError;
import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPActions.FTPAction;
import alvarodelrosal.ftp.modelo.FTPActions.FTPActionsFactory;
import alvarodelrosal.ftp.modelo.FTPActions.FTPLoginSuccess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FTPConnection extends Thread {

    private static final String TOKEN = "<:@:>";
    private Socket client;
    private PrintWriter output;
    private BufferedReader input;
    
    private FTPUser user = null;

    public FTPConnection(Socket client) {
        this.client = client;

        try {
            input = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
        } catch (IOException ex) {
            try {
                client.close();
            } catch (IOException ex1) {
            }
        }

        try {
            output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ex) {
            try {
                input.close();
            } catch (IOException ex1) {
            } finally {
                try {
                    client.close();
                } catch (IOException ex1) {
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            String inputRequest = input.readLine();

            FTPActionsFactory inputFactory = new FTPActionsFactory();
            FTPUsersRepository usersRepository = new FTPUsersRepository();

            while (continueActive(inputRequest)) {

                String command = getCommandFrom(inputRequest);
                String parameters = getParametersFrom(inputRequest);

                if (isTheUserLoggedIn()) {
                    FTPAction action = inputFactory.get(command);
                    action.doAction(output, parameters);
                } else {
                    String username = command;
                    String password = parameters;
                    
                    if (usersRepository.exists(username, password)) {
                        user = usersRepository.get(username, password);
                        
                        String userData = buildUserDataString();
                        
                        FTPLoginSuccess success = new FTPLoginSuccess();
                        success.doAction(output,userData);
                    } else {
                        FTPLoginError error = new FTPLoginError();
                        error.doAction(output, "");
                    }
                }
                inputRequest = input.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            output.close();
            try {
                input.close();
            } catch (IOException ex1) {
            } finally {
                try {
                    client.close();
                } catch (IOException ex1) {
                }
            }
        }
    }

    private String buildUserDataString() {
        String userData = user.getName();
        if(user.isIsAdmin()) {
            userData += "isAdmin"; 
        }
        return userData;
    }

    private boolean continueActive(String inputRequest) {
        return !"bye".equals(inputRequest);
    }

    private String getCommandFrom(String inputRequest) {
        return inputRequest.substring(0,inputRequest.indexOf(TOKEN));
    }

    private String getParametersFrom(String inputRequest) {
        return inputRequest.substring(inputRequest.indexOf(TOKEN)+TOKEN.length(),inputRequest.length());
    }

    private boolean isTheUserLoggedIn() {
        return user != null;
    }
}
