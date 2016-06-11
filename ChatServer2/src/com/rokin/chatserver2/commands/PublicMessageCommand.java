/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.commands;

import com.rokin.chatserver2.entity.Client;
import java.io.IOException;


/**
 *
 * @author rokin
 */
public class PublicMessageCommand extends ChatCommands{
    

    @Override
    public void execute(Client sender, String[] messageTokens) throws IOException {
       clientHandler.broadcastMessage(sender, messageTokens[0]);
    }

    
}
