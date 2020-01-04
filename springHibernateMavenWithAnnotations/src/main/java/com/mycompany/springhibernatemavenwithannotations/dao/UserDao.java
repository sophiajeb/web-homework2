/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springhibernatemavenwithannotations.dao;

import com.mycompany.springhibernatemavenwithannotations.model.User;
import java.util.List;

/**
 *
 * @author user
 */
public interface UserDao {
    
    User findById(int user_id);
    
    List<User> findAllUsers();
    
    void saveUser(User user);
    
    void deleteUserById(int user_id);
    
}
