package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;

public class TcpServerSocket extends Thread{

    private Socket clientSocket;


    public TcpServerSocket(Socket clientSocket) throws Exception{
        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket(){
        return this.clientSocket;
    }

    public void run(){
        try{
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            new Receive(this.clientSocket).start();
            new Send(this.clientSocket).start();
        }catch(Exception e){}
    }
}
