/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.commands;

import com.rokin.chatserver2.entity.Client;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author rokin
 */
public class ListAllCommand extends ChatCommands{

    @Override
    public void execute(Client sender, String[] messageTokens) throws IOException {
        PrintStream printer = new PrintStream(sender.getSocket().getOutputStream());
        PrivateMessageCommand pm = new PrivateMessageCommand();
        for(Client c : clientHandler.getClients())
        {
            printer.println(c.getUsername());
        }
    }
    
}
