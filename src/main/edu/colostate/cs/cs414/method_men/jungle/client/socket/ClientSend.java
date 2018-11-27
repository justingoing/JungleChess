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

    //Should get username and password as combined string
    //with space in between Ex: "username password"
    public void sendLogin(String data){
        out.println("login " + data);
        out.flush();
        System.out.println("Data sent: " + data);
    }

    //Method to send game state to server
    public void sendGameState(Object gameState) throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(gameState);
        oos.close();
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
