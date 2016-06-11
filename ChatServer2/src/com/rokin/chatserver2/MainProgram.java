/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2;

import com.rokin.chatserver2.handler.ClientHandler;
import com.rokin.chatserver2.handler.ClientListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author rokin
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            int port = 7777;
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server running at port " + port);
            ClientHandler clientHandler = new ClientHandler();
            while(true)
            {
                
                Socket client = server.accept();
                System.out.println("Connection request from " + client.getInetAddress().getHostAddress());
                
                ClientListener listener = new ClientListener(client, clientHandler);
                listener.start();
                
            }
            
            
            
        } catch (IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
        
    }
    
}
