package edu.colostate.cs.cs414.method_men.jungle.client.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private Scanner scanner;

    private Client(InetAddress address, int port) throws Exception{
        this.socket = new Socket(address,port);
        this.scanner = new Scanner(System.in);
    }

    private void start() throws IOException{
        String msg;
        while(true){
            msg = scanner.nextLine();
            PrintWriter send = new PrintWriter(this.socket.getOutputStream(), true);
            send.println(msg);
            send.flush();
            System.out.println("Message sent: " + msg);
        }

    }

    public static void main(String[] args) throws Exception{
        //using localHost for now
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Client client = new Client(address, 2000);
        System.out.println("Connected to server at " + address.toString() + " on port 2000");
        System.out.println("Type a message...");
        client.start();



    }
}
