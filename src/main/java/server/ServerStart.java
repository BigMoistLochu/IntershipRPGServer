package server;


import server.client.Servlet;

public class ServerStart {
    public static void main(String[] args) throws InterruptedException {
        Servlet servlet = new Servlet();
        new Thread(servlet).start();


    }


}