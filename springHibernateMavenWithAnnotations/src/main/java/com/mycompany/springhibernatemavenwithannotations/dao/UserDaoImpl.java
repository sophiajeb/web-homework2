/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springhibernatemavenwithannotations.dao;

import com.mycompany.springhibernatemavenwithannotations.model.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    
    public User findById(int user_id) {
		return getByKey(user_id);
	}
    
    
    public void saveUser(User user) {
		persist(user);
	}
    
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
	Criteria criteria = createEntityCriteria();
	return (List<User>) criteria.list();
    }  
    
    public void deleteUserById(int user_id) {
		Query query = getSession().createSQLQuery("delete from user where user_id = :user_id");
		query.setInteger("user_id", user_id);
		query.executeUpdate();
	}
}
