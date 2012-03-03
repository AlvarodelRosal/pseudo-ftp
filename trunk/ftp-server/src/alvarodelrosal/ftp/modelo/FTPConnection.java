package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPActions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FTPConnection extends Thread {

    
    public static final String TOKEN = "<:@:>";
    private Socket client;
    private PrintWriter output;
    private BufferedReader input;

    private FTPUser ftpUser = null;

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
            FTPBye bye = new FTPBye();

            while (mustKeepsExecuting(bye, inputRequest)) {
                String command = getsTheCommand(inputRequest);
                List<String> parameters = getsTheParameters(inputRequest);
                
                logsInTheUserOrExecutesTheAction(inputFactory, command, parameters, inputRequest, usersRepository);
                
                inputRequest = input.readLine();
            }
            
            
            output.println(bye.doAction(new ArrayList()));
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closesALLTheConnections();
        }
    }

    private boolean mustKeepsExecuting(FTPBye bye, String inputRequest) {
        return !bye.getName().equals(inputRequest);
    }

    private void closesALLTheConnections() {
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

    private void logsInTheUserOrExecutesTheAction(FTPActionsFactory inputFactory, String command, List<String> parameters, String inputRequest, FTPUsersRepository usersRepository) {
        if (userIsLogedIn()) {
            doActionIfPrivileged(inputFactory, command, parameters);
        } else {
            tryToLoginOrGeneratesError(inputRequest, usersRepository);
        }
    }

    private void doActionIfPrivileged(FTPActionsFactory inputFactory, String command, List<String> parameters) {
        FTPAction action = inputFactory.getFTPAction(command);
        if (needsAdminPrivileges(action)) {
            executesTheActionOrSendsError(action, parameters);
        } else {
            doTheAction(action, parameters);
        }
    }

    private void tryToLoginOrGeneratesError(String inputRequest, FTPUsersRepository usersRepository) {
        String username = getsThePosition(inputRequest,0);
        String password = getsThePosition(inputRequest,1);
        
        if (usersRepository.exists(username, password)) {
            logsInTheUser(usersRepository, username, password);
        } else {
            sendsALoginError();
        }
    }

    private void logsInTheUser(FTPUsersRepository usersRepository, String username, String password) {
        this.ftpUser = usersRepository.getUser(username, password);
        FTPHello hello = new FTPHello();
        output.println(hello.doAction(new ArrayList()));
    }

    private void doTheAction(FTPAction action, List<String> parameters) {
        output.println(action.doAction(parameters));
    }

    private void executesTheActionOrSendsError(FTPAction action, List<String> parameters) {
        if (ftpUser.isAdmin()) {
            doTheAction(action, parameters);
        } else {
            showsPrivilegesError();
        }
    }

    private void showsPrivilegesError() {
        FTPPrivilegesError error = new FTPPrivilegesError();
        output.println(error.doAction(new ArrayList()));
    }

    private boolean needsAdminPrivileges(FTPAction action) {
        return action.needsAdminPrivileges();
    }

    private boolean userIsLogedIn() {
        return ftpUser != null;
    }

    private void sendsALoginError() {
        FTPLoginError error = new FTPLoginError();
        output.println(error.doAction(new ArrayList()));
    }

    private String getsThePosition(String inputRequest, int position) {
        return inputRequest.split(TOKEN)[position];
    }
    
    private String getsTheCommand(String inputRequest) {
        return getsThePosition(inputRequest, 0);
        
    }

    private List<String> getsTheParameters(String inputRequest) {
        List<String> parameters = new ArrayList();
        String[] parametersArray = inputRequest.split(TOKEN);
        for(int position = 1; position < parametersArray.length; position++) {
            parameters.add(parametersArray[position]);
        }
        return parameters;
        
    }
}
