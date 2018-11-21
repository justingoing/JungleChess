package edu.colostate.cs.cs414.method_men.jungle.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;

public class TCPServer{

    private ServerSocket serverSocket;
    private ArrayList<User> users;
    public int threadCount = 0;

    private TCPServer(int port){
        //List of current users
        this.users = new ArrayList<>();
        //Server Socket
        try{
            this.serverSocket = new ServerSocket(port);
        }catch(Exception e){}
    }

    public void start(TCPServer server) throws Exception{
        System.out.println("Server Started");
        System.out.println("Waiting for connection");
        while(true){
            new TcpServerSocket(serverSocket.accept(), server).start();
            System.out.println("Connection accepted.");
            threadCount++;
            System.out.println("ThreadCount: " + threadCount);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User u){
        this.users.add(u);
        for(int i = 0; i < this.users.size(); i ++){
            System.out.println(this.users.get(i).getUsername());
        }
    }

    public static void main(String[] args) throws Exception{
        TCPServer server = new TCPServer(2000);
        server.start(server);
    }
}
