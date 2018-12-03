package edu.colostate.cs.cs414.method_men.jungle.server;

import java.net.Socket;

public class TcpServerSocket extends Thread{

    private Socket clientSocket;
    private TCPServer server;

    public TcpServerSocket(Socket clientSocket, TCPServer server) throws Exception{
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public Socket getClientSocket(){
        return this.clientSocket;
    }

    //this is messing things up, too many threads
    @Override
    public void run(){
        try{
            Receive r = new Receive(this.clientSocket, this.server);
            Send s = new Send(this.clientSocket, this.server);
            r.start();
            s.start();
        }catch(Exception e){
            System.out.println("Socket or IO: " + e);
        }
    }
}
