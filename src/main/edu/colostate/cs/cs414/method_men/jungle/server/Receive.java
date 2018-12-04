package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
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
            server.getSQL().updateMatchState(wholeString, gameID);
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
                boolean value = addInvite(message[1], message[2]);
                System.out.println("update? " + value);
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
        if(message[0].equals(("mySentInvites"))){
            List<String> mySentInvites = server.getSQL().searchMatchInviter(message[1]);
            if(mySentInvites.isEmpty()){
                try{
                    Send send = new Send(this.socket, this.server, this.gameID);
                    send.sendString("Fail");
                }catch (Exception e){}
            }else{
                try{
                    Send send = new Send(this.socket, this.server, this.gameID);
                    String invites = "";
                    for(int i = 0; i < mySentInvites.size(); i++){
                        invites += mySentInvites.get(i);
                        if(i < mySentInvites.size()-1){
                            invites += " ";
                        }
                    }
                    send.sendString(invites);
                }catch (Exception e){}
            }
        }

        if(message[0].equals("AcceptInvite")){
            String state = buildDefaultGameState(message[1], message[2]);
            System.out.println("State = " + state);
            String date = getDateTime();
            System.out.println("datetime = " + date);
            long matchID = server.getSQL().addMatchState(message[1], message[2], state, date);
            System.out.println("match added with id: " + matchID);
            boolean deleted = server.getSQL().deleteMatchInvite(message[1]);
            System.out.println("Deleted = " + deleted);
            //do we need to send matchID back to client???
        }

        if(message[0].equals("RejectInvite")){
            boolean deleted = server.getSQL().deleteMatchInvite(message[1]);
            System.out.println("Deleted = " + deleted);
        }

    }

    public String buildDefaultGameState(String user1, String user2){
        String state = "";
        state += "BluePlayer:" + user1 +  " RedPlayer:" + user2 +  " Winner:-1 NextTurn:Blue MoveCount:0 Red:7,0,0/6,0,6/4,1,1/2,1,5/1,2,0/5,2,2/3,2,4/8,2,6 Blue:8,6,0/3,6,2/5,6,4/1,6,6/2,7,1/4,7,5/6,8,0/7,8,6";
        return state;
    }

    public String getDateTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
        String dateTime = dateFormat.format(date);
        //System.out.println(dateTime);
        return dateTime;
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
