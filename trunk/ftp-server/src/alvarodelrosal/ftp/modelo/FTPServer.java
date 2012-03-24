package alvarodelrosal.ftp.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class FTPServer {

    private static boolean stop = false;

    public static void main(String args[]) throws IOException {
        Socket client = null;
        
        try {
            ServerSocket server = new ServerSocket(9999);
            server.setSoTimeout(3000);
            
            String file = "config.txt";
            BufferedReader reader = new BufferedReader(new FileReader(
                    new File(file)));
            
            String usersFile = reader.readLine();
            String inbox = reader.readLine();
            
            while (!stop) {
                try {
                    client = server.accept();
                    FTPConnection connection = new FTPConnection(client, usersFile, inbox);
                    connection.start();
                } catch (SocketTimeoutException e) {
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
