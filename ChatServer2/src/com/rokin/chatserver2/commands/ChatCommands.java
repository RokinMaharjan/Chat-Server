/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.commands;

import com.rokin.chatserver2.entity.Client;
import com.rokin.chatserver2.handler.ClientHandler;
import java.io.IOException;

/**
 *
 * @author rokin
 */
public abstract class ChatCommands {
    ClientHandler clientHandler;

    public ChatCommands() {
    }

    public ChatCommands(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

  
    
    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }


    
    public abstract void execute(Client sender, String[] messageTokens) throws IOException;
}
