package server.client.model;

import java.io.IOException;
import java.util.*;

public class PlayerContainer {

    private static final Map<Integer,Player> playerMap = new HashMap<>();
    public static final Queue<String> playerQueue = new PriorityQueue<>();
    private static final List<MessageEvent> listOfThread = new ArrayList<>();

    public static void addPlayerToContainer(Player player){
        playerMap.put(player.getId(), player);
        System.out.println("Postac zmienila pozycje: " + playerMap.get(player.getId()));
        //tutaj trzeba dodac zeby powiadomilo wszystkie dostepne watki do wyslania wiadomosci
        playerQueue.add("Dodalem wiadomosc");

        listOfThread.forEach(messageEvent -> {
            try {
                messageEvent.send();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void registerThread(MessageEvent messageEvent){
        listOfThread.add(messageEvent);
    }






}
