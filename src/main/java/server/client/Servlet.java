package server.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servlet implements Runnable{

    public Servlet(){ System.out.println("Server nasluchuje na porcie 8080"); }

    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(8080);

            while (true) {
                Socket client = socket.accept();

                GameClient gameClient = new GameClient(client);
                new Thread(gameClient).start();
            }


        }catch (IOException e){
            System.out.println("Port 8080 jest aktualnie zajety");
        }
    }




}
