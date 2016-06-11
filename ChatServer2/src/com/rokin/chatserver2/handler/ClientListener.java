/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.handler;

import com.rokin.chatserver2.userdao.UserDAO;
import com.rokin.chatserver2.userdao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;



/**
 *
 * @author rokin
 */
public class ClientListener extends Thread {
    
    private Socket client;
    PrintStream printer;
    private ClientHandler clientHandler;
    
    
    public ClientListener(Socket client, ClientHandler clientHandler) throws IOException {
        this.client = client;
        printer = new PrintStream(client.getOutputStream());
        this.clientHandler = clientHandler;
       
    }
    
    
    
    
    
    public void menu() throws IOException
    {
        
        printer.println("1. Login");
        printer.println("2. Register");
        
    }

    @Override
    public void run() {
        UserDAO userDAO = new UserDAOImpl();
        while(true)
        {
            
            
            try {
                menu();
                Scanner input = new Scanner(new InputStreamReader(client.getInputStream()));
                RequestHandler requestHandler = new RequestHandler(client, userDAO,clientHandler);
                userDAO.initializeUsers();
                switch(input.nextInt())
                {
                    case 1:
                        requestHandler.doLogin();
                        break;
                        
                    case 2:
                        requestHandler.registerUser();
                        break;
                        
                    default:
                        printer.println("Invalid choice");
                }
                    
                
                
            } catch (IOException ex) {
                System.out.println("Exception " + ex.getMessage());
            }
            
        }
    }
    
    
    
    
}
