package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;

/**
 * Thread for sending on the server
 */
public class Send extends Thread{

    private Scanner scanner;
    private PrintWriter out;
    private TCPServer server;

    /**
     * constructs a thread for sending
     * @param socket socket connection
     * @param server current server
     * @throws Exception IOException
     */
    public Send(Socket socket, TCPServer server) throws Exception{
        this.scanner = new Scanner(System.in);
        this.server = server;
        this.out = new PrintWriter(socket.getOutputStream(),true);
    }

    /**
     * Method for sending
     */
    public void send(){
        String msg;
        while(true){
            System.out.println("Send is running");
            msg = scanner.nextLine();
            if(msg !=null){
                for(int i = 0; i < server.getUsers().size(); i++){
                    try{
                        server.getUsers().get(i).send(msg);
                        System.out.println("Message sent to: " + server.getUsers().get(i).getUsername());
                    }catch (Exception e){
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }
    }

    /**
     * Sends the string
     * @param data string to send
     */
    public void sendString(String data){
        out.println(data);
        out.flush();
        System.out.println("Data sent");
    }

    /**
     * Sends response from login request
     * @param b success or fail
     */
    public void sendLoginResponse(boolean b){
        String s = "loginResponse ";
        respond(b, s);
    }

    /**
     * sends response from register request
     * @param b success or fail
     */
    public void sendRegisterResponse(boolean b){
        String s = "registerResponse ";
        respond(b, s);
    }

    /**
     * simplified method for both register and login response
     * @param b success or fail
     * @param s string
     */
    private void respond(boolean b, String s){
        if(b){
            out.println(s += "true");
            out.flush();
            System.out.println(s);
        }
        else{
            out.println(s += "false");
            out.flush();
            System.out.println(s);
        }
    }

    /**
     * overridden run method
     */
    public void run(){
        send();
    }
}
