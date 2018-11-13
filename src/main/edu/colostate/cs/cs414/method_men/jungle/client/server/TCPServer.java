package edu.colostate.cs.cs414.method_men.jungle.client.server;

import java.net.InetAddress;
import java.net.ServerSocket;

public class TCPServer{

    private ServerSocket serverSocket;
    public int threadCount = 0;

    public void start(int port) throws Exception{
        serverSocket = new ServerSocket(port);
        while(true){
            new TcpServerSocket(serverSocket.accept()).start();
            System.out.println("Connection accepted.");
            threadCount++;
            System.out.println("ThreadCount: " + threadCount);
        }
    }

    public InetAddress getSocketAddress(){
        return this.serverSocket.getInetAddress();
    }



    public static void main(String[] args) throws Exception{
        TCPServer server = new TCPServer();
        server.start(2000);
        System.out.println("Server Started");
        System.out.println("Waiting for connection");
    }
}
