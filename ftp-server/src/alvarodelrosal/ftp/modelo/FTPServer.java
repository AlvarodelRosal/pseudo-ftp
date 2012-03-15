package alvarodelrosal.ftp.modelo;

import alvarodelrosal.ftp.infraestructura.FTPUsersRepository;
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
            
            while (!stop) {
                try {
                    client = server.accept();
                    FTPConnection connection = new FTPConnection(client);
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
