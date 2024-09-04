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
            Socket client = socket.accept();

            GameClient gameClient = new GameClient(client);
            new Thread(gameClient);


            //czekamy na wiadomosc od klienta
            InputStream inputStream = client.getInputStream();
            //wysylamy wiadomosc do klienta
            OutputStream outputStream = client.getOutputStream();


            System.out.println(getMessage(inputStream));

        }catch (IOException e){
            System.out.println("Cos nie tak ze streamem");
        }
    }


    public static StringBuilder getMessage(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int _byte;
        while ((_byte=inputStream.read())>=0){
            stringBuilder.append((char) _byte);
        }


        return stringBuilder;
    }
}