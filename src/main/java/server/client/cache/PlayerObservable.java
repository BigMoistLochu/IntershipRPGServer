package server.client.cache;

import server.client.Observer;
import server.client.model.Player;

public interface PlayerObservable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Player player);
}
