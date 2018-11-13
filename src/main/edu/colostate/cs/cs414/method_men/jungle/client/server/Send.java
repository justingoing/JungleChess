package edu.colostate.cs.cs414.method_men.jungle.client.server;

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
        System.out.println("Send thread started");
        String msg = null;
        while(true){
            msg = scanner.nextLine();
            out.println(msg);
            out.flush();
            System.out.println("Message sent: " + msg);
        }
    }

    public void run(){
        send();
    }
}
