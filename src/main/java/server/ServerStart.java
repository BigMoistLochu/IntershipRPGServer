package server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerStart {
    public static void main(String[] args) throws InterruptedException {
        Servlet servlet = new Servlet(8080);
    }


}