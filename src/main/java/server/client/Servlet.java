package server.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servlet implements Runnable{

    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(8080);

            while (true) {
                System.out.println("server nasluchuje na porcie 8080");
                Socket client = socket.accept();

                GameClient gameClient = new GameClient(client);
                new Thread(gameClient).start();
            }


        }catch (IOException e){
            System.out.println("Cos nie tak ze streamem");
        }
    }




}
