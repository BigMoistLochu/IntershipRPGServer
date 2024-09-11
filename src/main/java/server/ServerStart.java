package server;


public class ServerStart {
    public static void main(String[] args) throws InterruptedException {
        Servlet servlet = new Servlet();
        Thread servletThread = new Thread(servlet);
        servletThread.start();
    }


}