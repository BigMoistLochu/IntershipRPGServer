package server.client.model;

import java.io.IOException;

public interface Observer {
    void send(Player player) throws IOException;
}
