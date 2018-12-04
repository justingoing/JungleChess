package edu.colostate.cs.cs414.method_men.jungle.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.jdbi.v3.core.Jdbi;


public class TCPServer{

    private ServerSocket serverSocket;
    private ArrayList<User> users;
    public int threadCount = 0;
    private static TcpServerSocket t;
    private Jdbi jdbi;
    private SqlQueries SQL;
    private long gameID = 0;

    private TCPServer(int port){
        //List of current users
        this.users = new ArrayList<>();
        this.jdbi = SqlUtils.getJdbi();
        this.SQL = jdbi.onDemand(SqlQueries.class);
        //Server Socket
        try{
            this.serverSocket = new ServerSocket(port);
        }catch(Exception e){}
    }

    public void start(TCPServer server) throws Exception{
        System.out.println("Server Started");
        System.out.println("Waiting for connection");
        try{
            while(true) {
                gameID++;
                Socket cSocket = serverSocket.accept();
                t = new TcpServerSocket(cSocket, server, gameID);
                t.start();
                System.out.println("Connection accepted.");
                threadCount++;
                System.out.println("ThreadCount: " + threadCount);
            }
        }catch(Exception e){
            System.out.println("Server Thread error: " + e);
        }finally {
            if(serverSocket != null){
                System.out.println("closing");
                serverSocket.close();
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    public SqlQueries getSQL() {
        return SQL;
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
