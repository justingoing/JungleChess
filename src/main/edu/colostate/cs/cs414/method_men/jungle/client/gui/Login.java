package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import java.net.Socket;

public class Login {
    public static boolean authenticate(String username, String password, Socket client) throws Exception{
        //sends username and pw to server. Need
        //Need to implement real authentication procedures server-side
        ClientSend clientSend = new ClientSend(client);
        clientSend.sendLogin(username + " " + password);
        // hardcoded username and password
        if (username.equals("zane") && password.equals("123456")) {
            return true;
        }
        if (username.equals("steve") && password.equals("123456")) {
            return true;
        }
        if (username.equals("dave") && password.equals("123456")) {
            return true;
        }
        //Get response back from server
        //true if username and pw match, false otherwise
        //
        //  stuff here about that
        //
        //
        return false;
    }
}
