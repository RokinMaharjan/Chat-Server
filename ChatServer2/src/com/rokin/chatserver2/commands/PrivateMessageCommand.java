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
public class PrivateMessageCommand extends ChatCommands{

    @Override
    public void execute(Client sender, String[] messageTokens) throws IOException {
        Client receiver = clientHandler.getByUsername(messageTokens[1]);
        PrintStream printer = new PrintStream(receiver.getSocket().getOutputStream());
        if(messageTokens.length>=2)
        {
            printer.println("[Private Message]" + sender.getUsername() + ": " + messageTokens[2]);
        }
        else
        {
            printer.println("Invalid command");
        }
    }
}
