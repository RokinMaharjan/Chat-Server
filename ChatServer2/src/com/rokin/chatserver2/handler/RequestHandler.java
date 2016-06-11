/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.handler;

import com.rokin.chatserver2.userdao.UserDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import com.rokin.chatserver2.commands.ChatCommandFactory;
import com.rokin.chatserver2.commands.ChatCommands;
import com.rokin.chatserver2.entity.Client;
import com.rokin.chatserver2.entity.User;
import java.io.PrintStream;

/**
 *
 * @author rokin
 */
public class RequestHandler {
    
    private Socket client;
    private PrintStream printer;
    UserDAO userDAO;
    BufferedReader reader;
    private ClientHandler clientHandler;
    
    public RequestHandler(Socket client, UserDAO userDAO, ClientHandler clientHandler) throws IOException {
        this.client = client;
        this.userDAO = userDAO;
        printer = new PrintStream(client.getOutputStream());
        reader =  new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.clientHandler = clientHandler;
    }


   
    public void doLogin() throws IOException
    {
        printer.println("Username: ");
        String username = reader.readLine();
        printer.println("Password: ");
        String password = reader.readLine();
        
       
        if(userDAO.checkLogin(new User(username, password)))
        {
            
            clientHandler.addClient(new Client(client, username));
            printer.println("Welcome to Rokin's chat server");
            while (true)
            {
                ChatCommandFactory commandFactory = new ChatCommandFactory();
                String line = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                line = reader.readLine();
                String[] tokens = line.split(";;");
                Client sender = clientHandler.getBySocket(client);
                ChatCommands selectedCommand = commandFactory.chatCommands(tokens[0]);
                selectedCommand.setClientHandler(clientHandler);
                selectedCommand.execute(sender, tokens);
            }
        }
    }
        
    

    void registerUser() throws IOException {
        printer.println("Username: ");
        String username = reader.readLine();
        printer.println("Password: ");
        String password = reader.readLine();
        userDAO.addUser(new User(username, password));
    }
    
    
}
