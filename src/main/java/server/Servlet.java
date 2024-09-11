package server;

import server.client.GameClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servlet{

    private int port;

    public Servlet(int port){
        this.port = port;
        System.out.println("Server nasluchuje na porcie: " + port);
        startWorking();
    }

    private void startWorking(){
        try {
            ServerSocket socket = new ServerSocket(port);

            while (true) {
                Socket client = socket.accept();

                GameClient gameClient = new GameClient(client);
                new Thread(gameClient).start();
            }

        }catch (IOException e){
            System.out.println("Port " + port + " jest aktualnie zajęty.");
        }
    }
}
