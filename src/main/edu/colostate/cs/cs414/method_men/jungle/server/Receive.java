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

    private Receive(Socket socket, TCPServer server, long gameID) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        this.server = server;
        this.gameID = gameID;
    }

    private void receive(){
        String msg;
        try {
            while ((msg = in.readLine()) != null) {
                System.out.println("Message received: " + msg);
                //Parse input into string array
                String[] message = parseReceive(msg);
                respondToInput(message, msg);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    private String[] parseReceive(String msg) {
        return msg.split(" ");
    }

    private void respondToInput(String [] message, String wholeString){
        if(message[0].equals("login")){
            processLogin(message);
        }
        if(message[0].equals("register")){
           processRegister(message);
        }
        if(message[0].equals("GameState")){
            processGameState(message, wholeString);
        }
        if(message[0].equals("Invite")) {
            processInvite(message);
        }
        if(message[0].equals(("myInvites"))){
            processMyInvites(message);
        }
        if(message[0].equals(("mySentInvites"))){
           processMySentInvites(message);
        }
        if(message[0].equals("AcceptInvite")){
            processAcceptInvite(message);
        }
        if(message[0].equals("RejectInvite")){
            server.getSQL().deleteMatchInvite(message[1]);
        }
        if(message[0].equals("myGames")){
            processMyGame(message);
        }
        if(message[0].equals("GetState")){
            processGetState(message);
        }
    }

    private void processLogin(String [] message){
        if(authenticateUser(message[1], message[2])){
            try{
                Send send = new Send(this.socket, this.server, gameID);
                send.sendLoginResponse(true);
                User user = new User(message[1], this.socket);
                server.addUser(user);
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else{
            try {
                Send send = new Send(this.socket, this.server, gameID);
                send.sendLoginResponse(false);
            }catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }

    private void processRegister(String[] message){
        if(registerUser(message[1], message[2])){
            try{
                Send send = new Send(this.socket, this.server, gameID);
                send.sendRegisterResponse(true);
                User user = new User(message[1], this.socket);
                server.addUser(user);
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else{
            try {
                Send send = new Send(this.socket, this.server, gameID);
                send.sendRegisterResponse(false);
            }catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }

    private void processGameState(String[] message, String wholeString){
        server.getSQL().updateMatchState(wholeString, gameID);
        String [] getOtherUser  = message[3].split(":");
        try{
            for(User u: server.getUsers()){
                if(u.getUsername().equals(getOtherUser[1])){
                    u.send(wholeString);
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    private void processInvite(String[] message){
        boolean found = findUser(message[2]);
        if (!found){
            try{
                Send send = new Send(this.socket, this.server, this.gameID);
                send.sendString("Fail");
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else if(!server.getSQL().searchPairMatchInvite(message[1], message[2]).isEmpty()){
            try{
                Send send = new Send(this.socket, this.server, this.gameID);
                send.sendString("There");
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else if(server.getSQL().searchPairMatchInvite(message[1], message[2]).isEmpty()){
            try{
                Send send = new Send(this.socket, this.server, this.gameID);
                send.sendString("Success");
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
    }

    private void processMyInvites(String[] message){
        List<String> myInvites = server.getSQL().searchMatchInvitee(message[1]);
        myInvitesResponse(myInvites);
    }

    private void processMySentInvites(String[] message){
        List<String> mySentInvites = server.getSQL().searchMatchInviter(message[1]);
        myInvitesResponse(mySentInvites);
    }

    private void myInvitesResponse(List<String> mySentInvites){
        if(mySentInvites.isEmpty()){
            try{
                Send send = new Send(this.socket, this.server, this.gameID);
                send.sendString("Fail");
            }catch (Exception e){
                System.out.println("Exception: " + e);
            }
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
            }catch (Exception e){
                System.out.println("Exception: " + e);
            }
        }
    }

    private void processAcceptInvite(String[] message){
        String state = buildDefaultGameState(message[1], message[2]);
        String date = getDateTime();
        server.getSQL().addMatchState(message[1], message[2], state, date);
        server.getSQL().deleteMatchInvite(message[1]);
    }

    private void processMyGame(String[] message){
        List<Long> myGames = getUser1User2ID(message[1]);
        if(myGames.isEmpty()){
            System.out.println("no Games");
        }
        else{
            String games = "";
            for(int i = 0; i < myGames.size(); i++){
                String u1 = server.getSQL().searchMatchUser1FromID(myGames.get(i));
                String u2 = server.getSQL().searchMatchUser2FromID(myGames.get(i));
                String id = myGames.get(i).toString();
                String game = u1 + "," + u2 + "," + id;
                games += game + " ";
            }
            System.out.println(games);
            try{
                Send send = new Send(this.socket, this.server, this.gameID);
                send.sendString(games);
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
    }

    private void processGetState(String[] message){
        int id = Integer.parseInt(message[1]);
        Long id1 = new Long(id);
        String state = server.getSQL().searchStateMatchState(id1);
        System.out.println(state);
        try{
            Send send = new Send(this.socket, this.server, this.gameID);
            send.sendString(state);
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    private List<Long> getUser1User2ID(String user1){
        List<Long> out = SqlUtils.getJdbi().withHandle(h -> {
                    List<Long> name = h.createQuery("SELECT ID FROM match_state WHERE User1='" + user1 + "' OR User2='" + user1 +"'").mapTo(Long.class).list();
                    System.out.println(name);
                    return name;
                }
        );
        return out;
    }

    private String buildDefaultGameState(String user1, String user2){
        return "Winner:-1 NextTurn:Blue MoveCount:0 Red:7,0,0/6,0,6/4,1,1/2,1,5/1,2,0/5,2,2/3,2,4/8,2,6 Blue:8,6,0/3,6,2/5,6,4/1,6,6/2,7,1/4,7,5/6,8,0/7,8,6";
    }

    private String getDateTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
        return dateFormat.format(date);
    }

    private boolean findUser(String user){
        return null != server.getSQL().searchUser(user);
    }

    private boolean authenticateUser(String username, String password){
        return null == server.getSQL().searchUserPassword(username, password);
    }


    private boolean registerUser(String username, String password){
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
