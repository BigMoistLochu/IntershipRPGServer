package server.client;

import java.net.Socket;

public class GameClient implements Runnable{

    private final Socket socket;

    public GameClient(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("start nowego watku");
        //input
        //output


    }

    //
}
