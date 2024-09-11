package server.client.cache;

import server.client.Observer;
import server.client.model.Player;

import java.io.IOException;

public interface PlayerObservable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Player player) throws IOException;
}
