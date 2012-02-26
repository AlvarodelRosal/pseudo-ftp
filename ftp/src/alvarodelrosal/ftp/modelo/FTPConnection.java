package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.modelo.FTPActions.FTPLoginError;
import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
import alvarodelrosal.ftp.modelo.FTPActions.FTPAction;
import alvarodelrosal.ftp.modelo.FTPActions.FTPActionsFactory;
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

            String username = getUsernameFrom(inputRequest);
            String password = getPasswordFrom(inputRequest);
            String command = getCommandFrom(inputRequest);

            if (usersRepository.exists(username, password)) {
                FTPAction action = inputFactory.get(command);
                action.doAction(output, inputRequest);
            } else {
                FTPLoginError error = new FTPLoginError();
                error.doAction(output, "");
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

    private String getUsernameFrom(String inputRequest) {
        String[] requests = inputRequest.split(TOKEN);
        return requests[0];
    }

    private String getPasswordFrom(String inputRequest) {
        String[] requests = inputRequest.split(TOKEN);
        return requests[1];
    }

    private String getCommandFrom(String inputRequest) {
        String[] requests = inputRequest.split(TOKEN);
        return requests[2];
    }
}
