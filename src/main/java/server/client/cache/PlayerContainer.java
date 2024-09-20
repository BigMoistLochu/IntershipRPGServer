package server.client.cache;

import server.client.Observer;
import server.client.model.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlayerContainer implements PlayerObservable {


    private static PlayerContainer INSTANCE = null;
    private final List<Observer> messagePublisher = new ArrayList<>();
    private final Logger logger = Logger.getLogger(PlayerContainer.class.getName());


    private PlayerContainer(){}

    public synchronized static PlayerContainer getInstance(){
        if(INSTANCE==null){
            INSTANCE = new PlayerContainer();
        }
        return INSTANCE;
    }

    public synchronized void update(Player player){
        if(player!=null) notifyObservers(player);
        else logger.info("Podany player jest nullem");
    }

    @Override
    public void addObserver(Observer observer) {
        messagePublisher.add(observer);
        logger.info("Obserwator zostal dodany do listy");
    }

    @Override
    public void removeObserver(Observer observer) {
        messagePublisher.remove(observer);
        logger.info("Obserwator zostal usuniety z listy");
    }

    @Override
    public void notifyObservers (Player player){
        for (Observer observer : messagePublisher){
                observer.send(player);
        }
    }
}
