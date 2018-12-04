package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;

public class Send extends Thread{

    private Scanner scanner;
    private PrintWriter out;
    private TCPServer server;
    private Socket socket;

    public Send(Socket socket, TCPServer server) throws Exception{
        this.socket = socket;
        this.scanner = new Scanner(System.in);
        this.server = server;
        this.out = new PrintWriter(socket.getOutputStream(),true);
    }

    public void send(){
        //System.out.println("ClientSend thread started");
        String msg = null;
        while(true){
            System.out.println("Send is running");
            msg = scanner.nextLine();
            if(msg !=null){
                for(int i = 0; i < server.getUsers().size(); i++){
                    try{
                        server.getUsers().get(i).send(msg);
                        System.out.println("Message sent to: " + server.getUsers().get(i).getUsername());
                    }catch (Exception e){}
                }
                msg = null;
            }
            msg = null;
            //msg = scanner.nextLine();
            //out.println(msg);
            //out.flush();
            //System.out.println("Message sent: " + msg);
        }
    }

    public void sendOnce(String data){
        out.println(data);
        out.flush();
        System.out.println("Data sent");
    }

    public void sendLoginResponse(boolean b){
        String s = "loginResponse ";
        if(b == true){
            out.println(s += "true");
            out.flush();
            System.out.println(s);
        }
        else{
            out.println(s += "false");
            out.flush();
            System.out.println(s);
        }
    }

    public void sendRegisterResponse(boolean b){
        String s = "registerResponse ";
        if(b == true){
            out.println(s += "true");
            out.flush();
            System.out.println(s);
        }
        else{
            out.println(s += "false");
            out.flush();
            System.out.println(s);
        }
    }

    public void sendToAll(String msg){

    }

    public void run(){
        send();
    }
}
