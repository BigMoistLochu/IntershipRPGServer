package server;


import server.client.Servlet;

public class ServerStart {
    public static void main(String[] args) throws InterruptedException {
        Servlet servlet = new Servlet();
        Thread servletThread = new Thread(servlet);
        servletThread.start();
    }


}