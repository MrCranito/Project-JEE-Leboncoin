package com.supsale.dao;

import com.supsale.entities.User;


public interface UserDao {
    
    void create( User user ) throws DAOException;

    User find( long id ) throws DAOException;
    
    User findByEmail( String email ) throws DAOException;
    
    User LogIn(String username, String password) throws DAOException;
}
