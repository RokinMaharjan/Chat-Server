/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokin.chatserver2.userdao;

import com.rokin.chatserver2.entity.User;
import java.util.List;



/**
 *
 * @author rokin
 */
public interface UserDAO {
    boolean addUser(User user);
    List<User> getAll();
    boolean checkLogin(User user);
    void initializeUsers();
    
}
