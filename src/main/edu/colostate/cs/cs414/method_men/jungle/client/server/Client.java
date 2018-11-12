package edu.colostate.cs.cs414.method_men.jungle.client.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Socket socket;
    private Scanner scanner;
    private PrintWriter send;
    private BufferedReader receive;

    private Client(InetAddress address, int port) throws Exception{
        this.socket = new Socket(address,port);
        this.scanner = new Scanner(System.in);
    }

    private void start() throws IOException{
        String msg;
        while(true){
            msg = scanner.nextLine();
            send = new PrintWriter(this.socket.getOutputStream(), true);
            send.println(msg);
            send.flush();
            System.out.println("Message sent: " + msg);
        }

    }

    public void run(){
        String msg = null;
        System.out.println("Receive thread started");
        while (true) {
            try {
                //Buffered Reader reads from socket
                receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((msg = receive.readLine()) != null) {
                    System.out.println("Message received: " + msg);
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) throws Exception{
        //using localHost for now
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Client client = new Client(address, 2000);
        System.out.println("Connected to server at " + address.toString() + " on port 2000");
        System.out.println("Type a message...");
        Thread t1 = new Thread(client, "Receive");
        t1.start();
        client.start();

    }
}
