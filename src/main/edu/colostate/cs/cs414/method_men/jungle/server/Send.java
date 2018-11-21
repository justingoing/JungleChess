package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;

public class Send extends Thread{

    private Scanner scanner;
    private PrintWriter out;

    public Send(Socket socket) throws Exception{
        this.scanner = new Scanner(System.in);
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

    public void run(){
        send();
    }
}
