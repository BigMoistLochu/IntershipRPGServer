package server.client;

import server.client.model.Player;
import server.client.cache.PlayerContainer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GameClient implements Runnable, Observer {


    private final OutputStream outputPlayerStream;
    private final InputStream inputPlayerStream;
    private final PlayerContainer playerContainer = PlayerContainer.getInstance();
    private final Logger logger = Logger.getLogger(GameClient.class.getName());



    public GameClient(Socket client) throws IOException {
        this.outputPlayerStream = client.getOutputStream();
        this.inputPlayerStream = client.getInputStream();
    }

    @Override
    public void run() {
        startListeningThread(inputPlayerStream,this);
    }

    private void startListeningThread(InputStream inputStream,GameClient gameClient) {
        Thread listeningThread = new Thread(() -> {
            try {
                logger.info("Watek: " + Thread.currentThread()+" wystartowal i nasluchuje na wiadomosci");
                playerContainer.addObserver(gameClient);
                listenForMessages(inputStream);
            } catch (IOException e) {
                logger.warning("Blad z przetwarzaniem wiadomosci od klienta gry na watku: " + Thread.currentThread());
                playerContainer.removeObserver(gameClient);
            }
        });
        listeningThread.start();
    }


    @Override
    public void send(Player player){
        if(player != null){
            try {
                outputPlayerStream.write(player.serializedPlayerPosition());
                outputPlayerStream.write(255);
                outputPlayerStream.flush();
            }catch (IOException e){
                logger.warning("Blad przy wysylaniu wiadomosci do klienta na watku" + Thread.currentThread());
            }
        }
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
    private void listenForMessages(InputStream inputStream) throws IOException {
        int _byte;
        List<Integer> byteList = new ArrayList<>();

        while ((_byte = inputStream.read()) != -1){

            if(_byte != 255 ){
                byteList.add(_byte);
                continue;
            }

            //issue, check if byte is out of range, mean if you get 255 byte bcs x = -1
            Player player = new Player(byteList.get(0),byteList.get(1),byteList.get(2),byteList.get(3));
            playerContainer.update(player);
            byteList.clear();
        }

        logger.info("Byte wyniosl ujemny byte wiec nasluchiwanie na watku: " + Thread.currentThread() + " dobieglo konca");
    }


}
