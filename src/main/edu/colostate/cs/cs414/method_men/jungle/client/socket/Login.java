package edu.colostate.cs.cs414.method_men.jungle.client.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Login {
    public static boolean authenticate(String username, String password, Socket client) throws Exception{
        //sends username and pw to server.
        //Need to implement real authentication procedures server-side
        System.out.println("authenticate called");
        ClientSend clientSend = new ClientSend(client);
        clientSend.sendLogin(username + " " + password);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String msg = in.readLine();
        System.out.println(msg);
        boolean b = false;
        if(msg.equals("loginResponse true")){
            b = true;
        }
        else if(msg.equals("loginResponse false")){
            b = false;
        }
        System.out.println("b = " + b);
        return b;
    }
}
