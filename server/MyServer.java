package test.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    int port;
    boolean stop;
    ClientHandler ch;

    public MyServer(int port, ClientHandler ch){
        this.port = port;
        this.ch = ch;
    }
    public void start(){
        stop = false;
        new Thread(this::startServer).start();

    }

    private void startServer() {
        try{
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while(!stop){
                try {
                    Socket client = server.accept();
                    ch.handleClient(client.getInputStream(), client.getOutputStream());
                    ch.close();
                    client.close();
                } catch (SocketTimeoutException ignored) {}
            }
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        stop = true;
    }

}
