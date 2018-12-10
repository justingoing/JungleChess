package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class that handles user information
 */
public class User {

    private String username;
    private Socket socket;
    private PrintWriter out;

    /**
     * constructs a user with their name and socket. Also means for communicating with them.
     * @param username
     * @param socket
     */
    User(String username, Socket socket){
        this.username = username;
        this.socket = socket;
        try{
            this.out = new PrintWriter(socket.getOutputStream(),true);
        }catch (Exception e){System.out.println("Exception: " + e);}
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

    /**
     * send a string to the user.
     * @param s string to send.
     */
    public void send(String s){
        out.println(s);
        out.flush();
    }
}
