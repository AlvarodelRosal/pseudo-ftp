package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPActions.*;
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
                inputRequest = executeCommands(inputRequest, inputFactory, usersRepository);
            }
            saysBye();
            
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

    private void saysBye() {
        FTPBye bye = new FTPBye();
        bye.doAction(output,"");
    }

    private String executeCommands(String inputRequest, FTPActionsFactory inputFactory, FTPUsersRepository usersRepository) throws IOException {
        String command = getCommandFrom(inputRequest);
        String parameters = getParametersFrom(inputRequest);
        if (isTheUserLoggedIn()) {
            launcAction(inputFactory, command, parameters);
        } else {
            loginUserIfExists(command, parameters, usersRepository);
        }
        inputRequest = input.readLine();
        return inputRequest;
    }

    private void loginUserIfExists(String command, String parameters, FTPUsersRepository usersRepository) {
        String username = command;
        String password = parameters;
        
        if (usersRepository.exists(username, password)) {
            validatesUser(usersRepository, username, password);
        } else {
            launchError();
        }
    }

    private void validatesUser(FTPUsersRepository usersRepository, String username, String password) {
        user = usersRepository.get(username, password);
        
        String userData = buildUserDataString();
        launchOk(userData);
    }

    private void launchOk(String userData) {
        FTPLoginSuccess success = new FTPLoginSuccess();
        success.doAction(output,userData);
    }

    private void launchError() {
        FTPLoginError error = new FTPLoginError();
        error.doAction(output, "");
    }

    private void launcAction(FTPActionsFactory inputFactory, String command, String parameters) {
        FTPAction action = inputFactory.get(command);
        action.doAction(output, parameters);
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
