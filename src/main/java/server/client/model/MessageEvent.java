package server.client.model;

import java.io.IOException;

public interface MessageEvent {
    void send() throws IOException;
}
