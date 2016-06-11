/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.commands;


/**
 *
 * @author rokin
 */
public class ChatCommandFactory {
    
    public ChatCommands chatCommands(String command)
    {
        if(command.equalsIgnoreCase("pm"))
        {
            return new PrivateMessageCommand();        
        }
        
        else if(command.equalsIgnoreCase("ls"))
        {
            return new ListAllCommand();
        }
        
        else if(command.equalsIgnoreCase("kick"))
        {
            return new KickCommand();
        }
        
        else if(command.equalsIgnoreCase("search"))
        {
            return new ScrapCommand();
        }
            
                    
        return new PublicMessageCommand();
    }
}
