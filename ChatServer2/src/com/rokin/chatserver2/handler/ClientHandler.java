/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.handler;

import com.rokin.chatserver2.entity.Client;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rokin
 */
public class ClientHandler {
   
    
    List<Client> clients = new ArrayList<>();
    
    public void addClient(Client client)
    {
        clients.add(client);
    }
    
    public void removeClient(Socket socket)
    {
        for(Client c : clients)
        {
            if(c.getSocket().equals(socket))
            {
                clients.remove(c);
            }
        }
          
    }
    
    public List<Client> getClients()
    {
        return clients;
    }
    
    
    public Client getByUsername(String username)
    {
        for(Client c : clients)
        {
            if(c.getUsername().equals(username))
            {
                return c;
            }
        }
        return null;
    }
    
    
    public Client getBySocket(Socket socket)
    {
        for(Client c : clients)
        {
            if(c.getSocket().equals(socket))
            {
                return c;
            }
        }
        return null;
    }
    
    public void broadcastMessage(Client sender, String message) throws IOException
    {
        
        for(Client c : clients)
        {
            if(!c.getSocket().equals(sender.getSocket()))
            {
                PrintStream printer = new PrintStream(c.getSocket().getOutputStream());
                printer.println(sender.getUsername() + ": " + message);
            }
        }
    }
}
