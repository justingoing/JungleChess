package edu.colostate.cs.cs414.method_men.jungle.server;

import java.net.Socket;

public class User {

    private String username;
    private Socket socket;

    User(String username, Socket socket){
        this.username = username;
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }
}
