package server.client.cache;

import server.client.Observer;
import server.client.model.Player;

import java.io.IOException;
import java.util.*;

public class PlayerContainer implements PlayerObservable {


    private static PlayerContainer INSTANCE = null;
    private final List<Observer> messagePublisher = new ArrayList<>();

    private PlayerContainer(){}

    public synchronized static PlayerContainer getInstance(){
        if(INSTANCE==null){
            INSTANCE = new PlayerContainer();
        }
        return INSTANCE;
    }

    public synchronized void update(Player player){
        if(player!=null) notifyObservers(player);
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
