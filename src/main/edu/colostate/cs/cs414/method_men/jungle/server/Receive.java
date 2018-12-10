package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *Receive thread. Handles all incoming requests to the server
 */
public class Receive extends Thread{

    private BufferedReader in;
    private Socket socket;
    private TCPServer server;
    private long gameID;

    /**
     * Constructs with a reader, socket, server, and gameID
     * @param socket Socket to client
     * @param server server currently running
     * @param gameID ID for game
     * @throws IOException
     */
    public Receive(Socket socket, TCPServer server, long gameID) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        this.server = server;
        this.gameID = gameID;
    }

    /**
     * Receives the message and passes it to respondToInput for processing
     */
    public void receive(){
        System.out.println("Receive is running");
        String msg;
        try {
            while ((msg = in.readLine()) != null) {
                System.out.println("Message received: " + msg);
                //Parse input into string array
                String[] message = msg.split(" ");
                respondToInput(message, msg);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Condition method for processing incoming requests. Each conditional has a child method that handles the
     * processing.
     * @param message array that holds each individual chunk.
     * @param wholeString String containing the message before .split.
     */
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

    /**
     * Processes login requests. Authenticates the user and sends a response.
     * @param message String containing the received message.
     */
    private void processLogin(String [] message){
        if(authenticateUser(message[1], message[2])){
            try{
                Send send = new Send(this.socket, this.server);
                send.sendLoginResponse(true);
                User user = new User(message[1], this.socket);
                server.addUser(user);
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else{
            System.out.println("in else");
            try {
                Send send = new Send(this.socket, this.server);
                send.sendLoginResponse(false);
            }catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }

    /**
     * Processes User Registration and responds with success or fail.
     * @param message String containing the received message.
     */
    private void processRegister(String[] message){
        if(registerUser(message[1], message[2])){
            try{
                Send send = new Send(this.socket, this.server);
                send.sendRegisterResponse(true);
                User user = new User(message[1], this.socket);
                server.addUser(user);
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else{
            try {
                Send send = new Send(this.socket, this.server);
                send.sendRegisterResponse(false);
            }catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }

    /**
     * Responsible for processing gameState messages.
     * @param message String containing the received message.
     * @param wholeString Whole String containing the received message.
     */
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

    /**
     * Responsible for processing invitations. Handles users that dont exist in the database, matches that already
     * exist, and successful invites respectively.
     * @param message String containing the received message.
     */
    private void processInvite(String[] message){
        boolean found = findUser(message[2]);
        if (!found){
            try{
                Send send = new Send(this.socket, this.server);
                send.sendString("Fail");
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else if(!server.getSQL().searchPairMatchInvite(message[1], message[2]).isEmpty()){
            try{
                Send send = new Send(this.socket, this.server);
                send.sendString("There");
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
        else if(server.getSQL().searchPairMatchInvite(message[1], message[2]).isEmpty()){
            try{
                Send send = new Send(this.socket, this.server);
                send.sendString("Success");
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
    }

    /**
     * Responsible for responding to requests for filling the incoming invitations table. Calls child method for sending
     * myInvitesResponse.
     * @param message array containing the message
     */
    private void processMyInvites(String[] message){
        List<String> myInvites = server.getSQL().searchMatchInvitee(message[1]);
        myInvitesResponse(myInvites);
    }

    /**
     * Responsible for responding to requests for filling the outgoing invitations table. Calls child method for sending
     * myInvitesResponse.
     * @param message array containing the message.
     */
    private void processMySentInvites(String[] message){
        List<String> mySentInvites = server.getSQL().searchMatchInviter(message[1]);
        myInvitesResponse(mySentInvites);
    }

    /**
     * Sends responses for bot outgoing, and incoming invitations table.
     * @param mySentInvites List containing outgoing or incoming invitations
     */
    private void myInvitesResponse(List<String> mySentInvites){
        if(mySentInvites.isEmpty()){
            try{
                Send send = new Send(this.socket, this.server);
                send.sendString("Fail");
            }catch (Exception e){
                System.out.println("Exception: " + e);
            }
        }else{
            try{
                Send send = new Send(this.socket, this.server);
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

    /**
     * Responsible for processing accepted invites. After accept, creates that match state and deletes the invitation
     * in the database.
     * @param message String containing the received message.
     */
    private void processAcceptInvite(String[] message){
        String state = buildDefaultGameState();
        String date = getDateTime();
        server.getSQL().addMatchState(message[1], message[2], state, date);
        server.getSQL().deleteMatchInvite(message[1]);
    }

    /**
     * Responsible for processing requests to see ongoing games table. Handles no current games and many ongoing games
     * respectively.
     * @param message String containing the received message.
     */
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
                Send send = new Send(this.socket, this.server);
                send.sendString(games);
            }catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
    }

    /**
     * Responsible for processing requests for current game state.
     * @param message String containing the received message.
     */
    private void processGetState(String[] message){
        int id = Integer.parseInt(message[1]);
        Long id1 = new Long(id);
        String state = server.getSQL().searchStateMatchState(id1);
        System.out.println(state);
        try{
            Send send = new Send(this.socket, this.server);
            send.sendString(state);
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Responsible for getting request for user ids from database.
     * @param user1 String containing user 1's id.
     * @return List containing user ids paired with requested username.
     */
    private List<Long> getUser1User2ID(String user1){
        List<Long> out = SqlUtils.getJdbi().withHandle(h -> {
                    List<Long> name = h.createQuery("SELECT ID FROM match_state WHERE User1='" + user1 + "' OR User2='" + user1 +"'").mapTo(Long.class).list();
                    System.out.println(name);
                    return name;
                }
        );
        return out;
    }

    /**
     * builds the default game state for new matches.
     * @return String containing the default game state.
     */
    private String buildDefaultGameState(){
        return "Winner:-1 NextTurn:Blue MoveCount:0 Red:7,0,0/6,0,6/4,1,1/2,1,5/1,2,0/5,2,2/3,2,4/8,2,6 Blue:8,6,0/3,6,2/5,6,4/1,6,6/2,7,1/4,7,5/6,8,0/7,8,6";
    }

    /**
     * Creates a date in the format our database uses.
     * @return String containing the current date.
     */
    private String getDateTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
        return dateFormat.format(date);
    }

    /**
     * searches for user in database.
     * @param user String containing the user id.
     * @return Boolean if user was found.
     */
    private boolean findUser(String user){
        if(null != server.getSQL().searchUser(user)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * performs user authentication in database.
     * @param username String of requested username
     * @param password String of requested users password
     * @return boolean if user exists in database
     */
    private boolean authenticateUser(String username, String password){
        if(null == server.getSQL().searchUserPassword(username, password)){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * registers user in database. handles user already exists and doesnt exist.
     * @param username String of requested username to register
     * @param password String of requested username password to register
     * @return boolean of success or fail
     */
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

    /**
     * overridden run method
     */
    public void run(){
        receive();
    }
}
