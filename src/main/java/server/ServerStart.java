package server;


import server.client.GameClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {
    public static void main(String[] args) {
        try {
            //localhost:8080/
            //"Tworzymy Socket na naszym komputerze ktory bedzie nasluchiwal na porcie 8080"

            ServerSocket socket = new ServerSocket(8080);

            while (true) {
                Socket client = socket.accept();

                GameClient gameClient = new GameClient(client);
                new Thread(gameClient).start();
            }


        }catch (IOException e){
            System.out.println("Cos nie tak ze streamem");
        }
    }


}