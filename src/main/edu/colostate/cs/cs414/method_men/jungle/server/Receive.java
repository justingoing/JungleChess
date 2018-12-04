package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.*;

public class Receive extends Thread{

    private BufferedReader in;
    private Socket socket;
    private TCPServer server;

    public Receive(Socket socket, TCPServer server) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        this.server = server;
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
                respondToInput(message, msg);
            }
        } catch (Exception e) {}
    }

    public String[] parseReceive(String msg) {
        String [] items = msg.split(" ");
        return items;
    }

    public void respondToInput(String [] message, String wholeString){
        //if logging in, do some stuff to send to database to authenticate, etc.
        //TODO
        if(message[0].equals("login")){
            //will need to check/call authentication methods here
            if(authenticateUser(message[1], message[2])){
                try{
                    Send send = new Send(this.socket, this.server);
                    send.sendLoginResponse(true);
                    User user = new User(message[1], this.socket);
                    server.addUser(user);
                }catch(Exception e){}
            }
            else{
                try {
                    Send send = new Send(this.socket, this.server);
                    send.sendLoginResponse(false);
                }catch (Exception e) {}
            }
        }
        if(message[0].equals("register")){
            if(registerUser(message[1], message[2])){
                try{
                    Send send = new Send(this.socket, this.server);
                    send.sendRegisterResponse(true);
                    User user = new User(message[1], this.socket);
                    server.addUser(user);
                }catch(Exception e){}
            }
            else{
                try {
                    Send send = new Send(this.socket, this.server);
                    send.sendRegisterResponse(false);
                }catch (Exception e) {}
            }
        }

        if(message[0].equals("GameState")){
            //send to database

            //send to other client
            String [] getOtherUser  = message[3].split(":");
            try{
                Send send = new Send(this.socket, this.server);
                for(User u: server.getUsers()){
                    if(u.getUsername().equals(getOtherUser[1])){
                        u.send(wholeString);
                        break;
                    }
                }
            }catch(Exception e){}
        }

    }

    public boolean authenticateUser(String username, String password){
        //need to query database for authentication here, for now hardcoded
        boolean u = false;
        boolean p = false;
        String user = server.getSQL().searchUser(username);
        //if username in db, check pw
        if(user.equals(username)){
            u = true;
            String pass = server.getSQL().searchUserPassword(username,password);
            if(pass.equals(username)){
                p = true;
            }
        }
        //returns true only if username and pw in db
        return (u && p);
    }


    public boolean registerUser(String username, String password){
        //need to check database for duplicate usernames here
        //will need to add user to database here as well
        //for now, just return true
        String user = server.getSQL().searchUser(username);
        if (!username.equals(user)){
            server.getSQL().addUser(username, password);
            return true;
        }
        else{
            return false;
        }
    }

    public void run(){
        receive();
    }
}
