package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.PrintWriter;
import java.net.Socket;

public class User {

    private String username;
    private Socket socket;
    private PrintWriter out;

    User(String username, Socket socket){
        this.username = username;
        this.socket = socket;
        try{
            this.out = new PrintWriter(socket.getOutputStream(),true);
        }catch (Exception e){}
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void send(String s){
        out.println(s);
        out.flush();
    }
}
