package edu.colostate.cs.cs414.method_men.jungle.client.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceive extends Thread{

    private BufferedReader in;
    private Socket socket;

    public ClientReceive(Socket socket) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
    }

    public void receive(){
        String msg = null;
        System.out.println("Receive thread started");
            try {
                //Buffered Reader reads from socket
                while ((msg = in.readLine()) != null) {
                    System.out.println("Message received: " + msg);
                    //Parse input into string array
                    String[] message = parseReceive(msg);
                    respondToInput(message);

                }
            } catch (Exception e) {}
    }

    public String recieveInviteResponse(){
        String msg = "";
        try{
            msg = in.readLine();
        }catch (Exception e){}
        return msg;
    }

    public void receiveLogin(){
        String msg = null;
        System.out.println("Getting Login Response");
        try {
            //Buffered Reader reads from socket
            msg = in.readLine();
            System.out.println("Login received: " + msg);
        } catch (Exception e) {}
    }

    public boolean receiveLoginResponse(String s) {
        if(s.equals("true")){
            return true;
        }
        else{
            return false;
        }
    }

    public String[] parseReceive(String msg) {
        String [] items = msg.split(" ");
        return items;
    }

    //planning on having this method handle the requests from the clients
    public void respondToInput(String [] message) throws Exception{
        //if logging in, do some stuff to send to database to authenticate, etc.
        //TODO
        if(message[0].equals("loginResponse")) {
            if(message[1].equals("true")){
                receiveLoginResponse("true");
            }
            else if(message[1].equals("false")){
                receiveLoginResponse("false");
            }

        }
    }

    public void close() throws Exception{
        in.close();
    }

    public void run(){
        receive();
    }
}
