package edu.colostate.cs.cs414.method_men.jungle.server;

import java.net.Socket;

/**
 * Socket class. Creates the threads that run on the server
 */
public class TcpServerSocket extends Thread{

    private Socket clientSocket;
    private TCPServer server;
    private long gameID;

    public TcpServerSocket(Socket clientSocket, TCPServer server, long gameID) throws Exception{
        this.clientSocket = clientSocket;
        this.server = server;
        this.gameID = gameID;
    }

    /**
     * overridden run method. runs the threads that listen for send or receive requests
     */
    @Override
    public void run(){
        try{
            new Receive(this.clientSocket, this.server, gameID).start();
            new Send(this.clientSocket, this.server, gameID).start();
        }catch(Exception e){
            System.out.println("Socket or IO: " + e);
        }
    }
}
