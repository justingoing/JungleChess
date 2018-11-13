package edu.colostate.cs.cs414.method_men.jungle.client.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive extends Thread{

    private BufferedReader in;

    public Receive(Socket socket) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void receive(){
        String msg = null;
        System.out.println("Receive thread started");
        while (true) {
            try {
                //Buffered Reader reads from socket
                while ((msg = in.readLine()) != null) {
                    System.out.println("Message received: " + msg);
                }
            } catch (Exception e) {
            }
        }
    }

    public void run(){
        receive();
    }
}
