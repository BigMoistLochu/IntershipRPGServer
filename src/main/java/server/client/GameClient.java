package server.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GameClient implements Runnable{

    private final Socket client;

    public GameClient(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("start nowego watku: " + Thread.currentThread());

        try {
            InputStream inputStream = client.getInputStream();
            //wysylamy wiadomosc do klienta
            OutputStream outputStream = client.getOutputStream();

            System.out.println(getMessage(inputStream));

        }catch (IOException e){
            System.out.println("blad komunikacja");
        }

    }


    public StringBuilder getMessage(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int _byte;
        while ((_byte=inputStream.read())>=0){
            stringBuilder.append((char) _byte);
        }


        return stringBuilder;
    }

}
