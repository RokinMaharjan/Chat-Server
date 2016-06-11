/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.userdao.impl;

import com.rokin.chatserver2.entity.User;
import com.rokin.chatserver2.userdao.UserDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rokin
 */
public class UserDAOImpl implements UserDAO{
    
    List<User> users = new ArrayList<>();

    
    
    @Override
    public boolean addUser(User user) {
        users.add(user);
        return true;
    }

    
    
    @Override
    public List<User> getAll() {
        
        return users;
    }

    @Override
    public boolean checkLogin(User user) {
        for(User u : getAll())
        {
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initializeUsers() {
        users.add(new User("rokin", "maharjan"));
        users.add(new User("sudin", "ranjitkar"));
        users.add(new User("prithivi", "pandey"));
    }
    
}
