package server.client.model;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class PlayerContainer implements Observable{


    private static PlayerContainer INSTANCE = null;
    private final List<Observer> messagePublisher = new ArrayList<>();

    public final Queue<Player> playerQueue = new LinkedBlockingQueue<>();

    private PlayerContainer(){}

    public synchronized static PlayerContainer getInstance(){
        if(INSTANCE==null){
            INSTANCE = new PlayerContainer();
        }

        return INSTANCE;
    }

    public void addPlayerToContainer(Player player){
        playerQueue.add(player);
        System.out.println(playerQueue);
        updateMessageQueue(playerQueue.remove());
    }

    public void updateMessageQueue(Player player){
        if(player!=null){
            notifyObservers(player);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        messagePublisher.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        messagePublisher.remove(observer);
    }

    @Override
    public void notifyObservers(Player player) {
        for (Observer observer : messagePublisher){
            try {
                observer.send(player);
            }catch (IOException e){
                System.out.println("Blad podczas wysylania wiadomosci");
            }
        }
    }
}
