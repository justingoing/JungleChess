package edu.colostate.cs.cs414.method_men.jungle.client.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TCPServer implements Runnable{

    private ServerSocket server;
    private Socket client;
    private Scanner scanner;


    //Creates server, listens on port
    public TCPServer(int port) throws Exception{
        this.server = new ServerSocket(port);
        this.scanner = new Scanner(System.in);
    }

    private void listen() throws Exception {
        String msg = null;
        this.client = this.server.accept();
        System.out.println("Socket Accepted on port: " + client.getLocalPort());
        Thread t = new Thread() {
            @Override
            public void run() {
                String msg = null;
                System.out.println("Receive thread started");
                while (true) {
                    String clientAddress = client.getInetAddress().getHostAddress();
                    try {
                        //Buffered Reader reads from socket
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        while ((msg = in.readLine()) != null) {
                            System.out.println("Message received from: " + clientAddress + ": " + msg);
                        }
                    } catch (Exception e) {
                    }
                }
            }

        };
        t.start();
    }

    private void send() throws IOException{
        System.out.println("Send thread started");
        String msg = null;
        while(true){
            msg = scanner.nextLine();
            PrintWriter send = new PrintWriter(this.client.getOutputStream(), true);
            send.println(msg);
            send.flush();
            System.out.println("Message sent: " + msg);
        }

    }

    public InetAddress getSocketAddress(){
        return this.server.getInetAddress();
    }

    public int getPort(){
        return this.server.getLocalPort();
    }

    //threading
    public void run(){
        try{
            this.send();
        }catch(IOException e){}
    }

    public static void main(String[] args) throws Exception{
        TCPServer server = new TCPServer(2000);
        System.out.println("Server Started");
        System.out.println("Waiting for connection");
        server.listen();
        Thread t1 = new Thread(server, "send");
        t1.start();

    }
}
