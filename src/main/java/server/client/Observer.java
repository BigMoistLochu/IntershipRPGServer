package server.client;

import server.client.model.Player;

public interface Observer {
    void send(Player player);
}
