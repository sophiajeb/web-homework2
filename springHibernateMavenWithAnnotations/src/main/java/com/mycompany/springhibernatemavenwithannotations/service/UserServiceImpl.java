/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springhibernatemavenwithannotations.service;

import com.mycompany.springhibernatemavenwithannotations.dao.UserDao;
import com.mycompany.springhibernatemavenwithannotations.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */

@Service("UserService")
@Transactional (readOnly = true)
public class UserServiceImpl implements UserService {
    
    
    @Autowired
    private UserDao dao;
    
    @Transactional
    @Override
    public User findById(int user_id) {
        return dao.findById(user_id);
    }
    
    @Transactional
    @Override
    public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
    
    
    @Override
    public void saveUser(User user) {
		dao.saveUser(user);
	}
    
    @Transactional
    @Override
    public void deleteUserById(int user_id) {
		dao.deleteUserById(user_id);
	}
    
    @Transactional
    @Override
    public void updateUser(User user) {
		User entity = dao.findById(user.getUser_id());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());
			entity.setFirst_name(user.getFirst_name());
			entity.setLast_name(user.getLast_name());
                        entity.setRole(user.getRole());
		}
	}
}
