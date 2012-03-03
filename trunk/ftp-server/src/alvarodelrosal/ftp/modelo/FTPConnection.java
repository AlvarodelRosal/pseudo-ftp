package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.modelo.FTPActions.FTPPrivilegesError;
import alvarodelrosal.ftp.modelo.FTPActions.FTPBye;
import alvarodelrosal.ftp.modelo.FTPActions.FTPLoginError;
import alvarodelrosal.ftp.modelo.FTPActions.FTPActionsFactory;
import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPActions.FTPAction;
import alvarodelrosal.ftp.modelo.FTPActions.FTPHello;
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

            while (!bye.getName().equals(inputRequest)) {
                String command = getsTheCommand(inputRequest);
                List<String> parameters = getsTheParameters(inputRequest);
                
                if (ftpUser != null) {
                    FTPAction action = inputFactory.getFTPAction(command);
                    if (action.needsAdminPrivileges()) {
                        if (ftpUser.isAdmin()) {
                            output.println(action.doAction(parameters));
                        } else {
                            FTPPrivilegesError error = new FTPPrivilegesError();
                            output.println(error.doAction(new ArrayList()));
                        }
                    } else {
                        output.println(action.doAction(parameters));
                    }
                } else {
                    String username = getsThePosition(inputRequest,0);
                    String password = getsThePosition(inputRequest,1);
                    
                    if (usersRepository.exists(username, password)) {
                        this.ftpUser = usersRepository.getUser(username, password);
                        FTPHello hello = new FTPHello();
                        output.println(hello.doAction(new ArrayList()));
                    } else {
                        sendsALoginError();
                    }
                }
                
                inputRequest = input.readLine();
            }
            
            
            output.println(bye.doAction(new ArrayList()));
            
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
