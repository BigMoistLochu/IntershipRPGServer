package server.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerContainer {

    private static final Map<Integer,Player> playerMap = new HashMap<>();
    private static final List<Thread> connectedPlayersList = new ArrayList<>();

    public static void addPlayerToContainer(Player player){
        playerMap.put(player.getId(), player);
        System.out.println("Postac zmienila pozycje: " + playerMap.get(player.getId()));
    }

    public static void registerThreadSender(Thread thread){
        connectedPlayersList.add(thread);
        System.out.println("Watek do wysylania zostal zarejestrowany");
    }





}
