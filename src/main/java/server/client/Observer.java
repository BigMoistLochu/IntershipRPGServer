package server.client;

import server.client.model.Player;

import java.io.IOException;

public interface Observer {
    void send(Player player);
}
