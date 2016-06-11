/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.commands;

import com.rokin.chatserver2.entity.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rokin
 */
public class ScrapCommand extends ChatCommands{
    

    @Override
    public void execute(Client sender, String[] messageTokens) throws IOException {
        URL url = new URL("http://www.jobsnepal.com/simple-job-search");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        
        OutputStream outputStream = urlConnection.getOutputStream();
        byte[] data = ("Keywords=" + messageTokens[1]).getBytes();
        outputStream.write(data);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line = "";
        StringBuilder builder = new StringBuilder();
        while((line = reader.readLine()) != null)
        {
            builder.append(line + "\n");
        }
        reader.close();
        
        String regex = "<a class=\"job-item\"(.*?)href=\"(.*?)\\s>\\n(.*?)</a>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(builder.toString());
        PrintStream printer = new PrintStream(sender.getSocket().getOutputStream());
        while(matcher.find())
        {
            printer.println("Job Title: " + matcher.group(3).trim());
            printer.println("Link: " + matcher.group(2).trim());
        }

        
    }
    
}
