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
            getMessage(inputStream);
        }catch (IOException e){
            System.out.println("Rozerwalo polaczenie z graczem");
        }

    }

    //protokol do gry:
    //potrzebujemy pozycje gracza do renderowania czyli np w bajcie mozna zapisac kierunek w jakim zmierza
    //Kierunek: 0-gora, 1-prawo, 2 dol, 3, lewo
    //pozycja gracza w 1 bajcie: x,y

    public void getMessage(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int _byte;
        byte a_byte = 4;
        while ((_byte=inputStream.read()) != -1){
            //czytaj ciagle dane jesli pojawi sie zero to wyswietl wiadomosc
            if(_byte==0){
                showMessage(stringBuilder);
                stringBuilder.setLength(0);
            }
            stringBuilder.append(_byte);
        }
    }

    public void showMessage(StringBuilder stringBuilder){
        System.out.println(stringBuilder);
    }
}
