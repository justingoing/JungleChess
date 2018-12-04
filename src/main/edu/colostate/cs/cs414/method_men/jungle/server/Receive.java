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
    private long gameID;

    public Receive(Socket socket, TCPServer server, long gameID) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        this.server = server;
        this.gameID = gameID;
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
                    Send send = new Send(this.socket, this.server, gameID);
                    send.sendLoginResponse(true);
                    User user = new User(message[1], this.socket);
                    server.addUser(user);
                }catch(Exception e){}
            }
            else{
                try {
                    Send send = new Send(this.socket, this.server, gameID);
                    send.sendLoginResponse(false);
                }catch (Exception e) {}
            }
        }
        if(message[0].equals("register")){
            if(registerUser(message[1], message[2])){
                try{
                    Send send = new Send(this.socket, this.server, gameID);
                    send.sendRegisterResponse(true);
                    User user = new User(message[1], this.socket);
                    server.addUser(user);
                }catch(Exception e){}
            }
            else{
                try {
                    Send send = new Send(this.socket, this.server, gameID);
                    send.sendRegisterResponse(false);
                }catch (Exception e) {}
            }
        }

        if(message[0].equals("GameState")){
            //send to database
            boolean isUpdated = server.getSQL().updateMatchState(wholeString, gameID);
            System.out.println("State is updated?: " + isUpdated);
            //send to other client
            String [] getOtherUser  = message[3].split(":");
            try{
                Send send = new Send(this.socket, this.server, gameID);
                for(User u: server.getUsers()){
                    if(u.getUsername().equals(getOtherUser[1])){
                        u.send(wholeString);
                        break;
                    }
                }
            }catch(Exception e){}
        }

        if(message[0].equals("Invite")) {
            System.out.println("Invite Received Inviter = " + message[1] + " Invitee = " + message[2]);
            System.out.println(server.getSQL().searchPairMatchInvite(message[1], message[2]));
            boolean found = findUser(message[2]);
            if (!found){
                try{
                    Send send = new Send(this.socket, this.server, this.gameID);
                    send.sendString("Fail");
                }catch(Exception e){}
            }
            else if(!server.getSQL().searchPairMatchInvite(message[1], message[2]).isEmpty()){
                //System.out.println(server.getSQL().searchPairMatchInvite(message[1], message[2]));
                try{
                    Send send = new Send(this.socket, this.server, this.gameID);
                    send.sendString("There");
                }catch(Exception e){}
            }
            else if(server.getSQL().searchPairMatchInvite(message[1], message[2]).isEmpty()){
                System.out.println("Found Invitee");
                addInvite(message[1], message[2]);
                try{
                    Send send = new Send(this.socket, this.server, this.gameID);
                    send.sendString("Success");
                }catch(Exception e){}
            }
        }
        if(message[0].equals(("myInvites"))){
            List<String> myInvites = server.getSQL().searchMatchInvitee(message[1]);
            if(myInvites.isEmpty()){
                try{
                    Send send = new Send(this.socket, this.server, this.gameID);
                    send.sendString("Fail");
                }catch (Exception e){}
            }else{
                try{
                    Send send = new Send(this.socket, this.server, this.gameID);
                    String invites = "";
                    for(int i = 0; i < myInvites.size(); i++){
                        invites += myInvites.get(i);
                        if(i < myInvites.size()-1){
                            invites += " ";
                        }
                    }
                    send.sendString(invites);
                }catch (Exception e){}
            }
        }

    }

    public boolean findUser(String user){
        if(null != server.getSQL().searchUser(user)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addInvite(String user1, String user2){
        return server.getSQL().addMatchInvite(user1, user2);
    }

    public boolean authenticateUser(String username, String password){
        //if username does not exist
        if(null == server.getSQL().searchUserPassword(username, password)){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean registerUser(String username, String password){
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
