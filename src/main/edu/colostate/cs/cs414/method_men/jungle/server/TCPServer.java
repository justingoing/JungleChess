package edu.colostate.cs.cs414.method_men.jungle.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.jdbi.v3.core.Jdbi;

/**
 * The main class for the server
 */
public class TCPServer{

    private ServerSocket serverSocket;
    private ArrayList<User> users;
    private int threadCount = 0;
    private SqlQueries SQL;
    private long gameID = 0;

    /**
     * Constructs a server and gets information from the database
     * @param port active port
     */
    private TCPServer(int port){
        this.users = new ArrayList<>();
        Jdbi jdbi = SqlUtils.getJdbi();
        this.SQL = jdbi.onDemand(SqlQueries.class);
        try{
            this.serverSocket = new ServerSocket(port);
        }catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }

    /**
     * overridden Method for starting the server
     * @param server object of type server
     * @throws Exception server exception, IOException
     */
    private void start(TCPServer server) throws Exception{
        System.out.println("Server Started");
        System.out.println("Waiting for connection");
        try{
            while(true) {
                gameID++;
                Socket cSocket = serverSocket.accept();
                TcpServerSocket t = new TcpServerSocket(cSocket, server, gameID);
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

    public SqlQueries getSQL() {
        return SQL;
    }

    /**
     * Adds user to local list
     * @param u user to be added
     */
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
