package edu.colostate.cs.cs414.method_men.jungle.client.socket;

import edu.colostate.cs.cs414.method_men.jungle.client.gui.GUI;

import javax.swing.*;
import java.net.InetAddress;
import java.net.Socket;


public class Client implements Runnable{

    private Socket socket;

    private Client(InetAddress address, int port) throws Exception{
        this.socket = new Socket(address,port);
    }

    public void run(){
        if(Thread.currentThread().getName().equals("Receive")){
            try{
                ClientReceive receive = new ClientReceive(this.socket);
                receive.receive();
            }catch(Exception e){}
        }
        if(Thread.currentThread().getName().equals("ClientSend")){
            try{
                ClientSend send = new ClientSend(this.socket);
                send.send();
            }catch(Exception e){}
        }
    }

    public static void main(String[] args) throws Exception{
        //using localHost for now
        //InetAddress address = InetAddress.getByName("184.60.76.255");
        InetAddress address = InetAddress.getByName("192.168.1.63");
        //InetAddress address = InetAddress.getByName("127.0.0.1");
        Client client = new Client(address, 2000);
        System.out.println("Connected to server at " + address.toString() + " on port 2000");
        //System.out.println("Type a message...");

        //Thread t1 = new Thread(client, "Receive");
        //Thread t2 = new Thread(client, "ClientSend");
        //t1.start();
        //t2.start();

        GUI g = new GUI(client.socket);
        g.startGUI();
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
