package server.client;

import server.client.model.Player;
import server.client.model.PlayerContainer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
            OutputStream outputStream = client.getOutputStream();
            startListening(inputStream);
            startSending(outputStream);
        }catch (IOException e){
            System.out.println("Rozerwalo polaczenie z graczem");
        }

    }

    public void startListening(InputStream inputStream){
        new Thread(()->{
            System.out.println("Watek co bedzie odpowiedzialny za odbieranie: " + Thread.currentThread());
            try {
                messageListener(inputStream);
            } catch (IOException e) {
                System.out.println("Blad w nasluchiwaniu na wiadomosc od klienta");
            }
        }).start();
    }

    public void startSending(OutputStream outputStream){
        Thread sendingThread = new Thread(()->{
            System.out.println("Watek co bedzie odpowiedzialny za wysylanie: " + Thread.currentThread());
        });
        //register this thread to container
        sendingThread.start();
    }


    /**
     * Protokół komunikacyjny służący do przesyłania informacji o pozycji i kierunku.
     * Protokół składa się z trzech bajtów:
     *      Pierwszy bajt zawiera unikalne id gracza (0-254)
     *
     *      Drugi bajt zawiera informację o pozycji X (0-254).
     *
     *      Trzeci bajt zawiera informację o pozycji Y (0-254).
     *
     *      Czwarty bajt zawiera informację o kierunku (direction),
     *      np. wartości liczbowe reprezentujące kierunek (np. 0 - północ, 1 - wschód, 2 - południe, 3 - zachód)
     *
     *      Ostatni bajt(255 zarezerwowane) zawsze reprezentuje koniec wiadomosci
     *Czyli przykladowe informacje: [10,20,2,255] -> x=10,y=20,direction = poludnie, koniec wiadomosci
     * @param inputStream strumień wejściowy zawierający dane protokołu
     * @throws IOException jeżeli wystąpi błąd podczas odczytu danych ze strumienia
     */
    public void messageListener(InputStream inputStream) throws IOException {

        int _byte;
        List<Integer> byteList = new ArrayList<>();
        while ((_byte = inputStream.read()) != -1){

            if(_byte != 255){
                byteList.add(_byte);
                continue;
            }

            Player player = new Player(byteList.get(0),byteList.get(1),byteList.get(2),byteList.get(3));
            PlayerContainer.addPlayerToContainer(player);
            byteList.clear();
        }

        System.out.println("Byte wyniosl -1 wiec nasluchiwanie na watku: " + Thread.currentThread() + " dobieglo konca");
    }

}
