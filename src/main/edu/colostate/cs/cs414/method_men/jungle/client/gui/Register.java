package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import edu.colostate.cs.cs414.method_men.jungle.client.socket.ClientSend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Register {

    public static boolean register(String username, String password, Socket client) throws Exception{
        //sends registration information to server.
        System.out.println("register called");
        ClientSend clientSend = new ClientSend(client);
        clientSend.sendRegister(username + " " + password);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String msg = in.readLine();
        System.out.println(msg);
        boolean b = false;
        if(msg.equals("registerResponse true")){
            b = true;
        }
        else if(msg.equals("registerResponse false")){
            b = false;
        }
        System.out.println("b = " + b);
        return b;
    }
}
