package edu.colostate.cs.cs414.method_men.jungle.client.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private ServerSocket server;


    //Creates server, listens on port
    public TCPServer(int port) throws Exception{
        this.server = new ServerSocket(port);
    }

    private void listen() throws Exception{
        String msg = null;
        Socket client = this.server.accept();
        System.out.println("Socket Accepted on port: " + client.getLocalPort());
        String clientAddress = client.getInetAddress().getHostAddress();
        //Buffered Reader reads from socket
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while((msg = in.readLine()) != null) {
            System.out.println("Message received from: " + clientAddress + ": " + msg);
            //System.out.println(msg);
            //msg = null;
        }
    }

    public InetAddress getSocketAddress(){
        return this.server.getInetAddress();
    }

    public int getPort(){
        return this.server.getLocalPort();
    }

    public static void main(String[] args) throws Exception{
        TCPServer server = new TCPServer(2000);
        System.out.println("Server Started");
        System.out.println("Waiting for connection");
        server.listen();
    }
}
