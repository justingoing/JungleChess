package edu.colostate.cs.cs414.method_men.jungle.client.socket;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend extends Thread{

    private Scanner scanner;
    private PrintWriter out;
    private Socket socket;

    public ClientSend(Socket socket) throws Exception{
        this.scanner = new Scanner(System.in);
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(),true);

    }

    public void send(){
        System.out.println("ClientSend thread started");
        String msg = null;
        while(true){
            msg = scanner.nextLine();
            out.println(msg);
            out.flush();
            System.out.println("Message sent: " + msg);
        }
    }

    public void sendState(String data){
        out.println("GameState " + data);
        out.flush();
        System.out.println("State sent: " + data);
    }

    public void sendInvite(String inviter, String invitee){
        out.println("Invite " + inviter + " " + invitee);
        out.flush();
        System.out.println("Invite sent");
    }

    public void lookupMyInvites(String invitee){
        out.println("myInvites " + invitee);
        out.flush();
        System.out.println("Lookup sent");
    }

    public void lookupMySentInvites(String inviter){
        out.println("mySentInvites " + inviter);
        out.flush();
        System.out.println("Lookup sent");
    }

    public void lookupMyGames(String username){
        out.println("myGames " + username);
        out.flush();
        System.out.println("myGames sent");
    }

    public void sendAccept(String user1, String user2){
        out.println("AcceptInvite " + user1 + " " + user2);
        out.flush();
        System.out.println("Accept invite sent");
    }

    public void sendReject(String user1, String user2){
        out.println("RejectInvite " + user1 + " " + user2);
        out.flush();
        System.out.println("Reject invite sent");
    }

    //Should get username and password as combined string
    //with space in between Ex: "username password"
    public void sendLogin(String data){
        out.println("login " + data);
        out.flush();
        System.out.println("Data sent: " + data);
    }

    public void sendRegister(String data){
        out.println("register " + data);
        out.flush();
        System.out.println("Data sent: " + data);
    }

    public void sendBoolean(boolean b){
        if(b == true){
            out.println("true");
            out.flush();
            System.out.println("Data sent");
        }
        else{
            out.println("false");
            out.flush();
            System.out.println("Data sent");
        }
    }

    public void close(){
        out.close();
    }
    public void run(){
        send();
    }
}
