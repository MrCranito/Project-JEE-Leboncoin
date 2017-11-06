package com.supsale.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supsale.entities.User;

@Stateless
public class UserDaoImpl implements UserDao{
    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM User u WHERE u.email=:email";
    private static final String JPQL_SELECT_USERNAME_PASSWORD = "SELECT u FROM User u WHERE u.username=:username AND u.password=:password";
  
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USERNAME = "username";

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "SupsalePU" )
    private EntityManager em;

    // Enregistrement d'un nouvel utilisateur
    @Override
    public void create( User user ) throws DAOException {
        try {
            em.persist( user );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
    
    @Override
    public User find( long id ) throws DAOException {
        try {
            return em.find( User.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    // Recherche d'un utilisateur à partir de son adresse email
    @Override
    public User findByEmail( String email ) throws DAOException {
        User user = null;
        Query request = em.createQuery( JPQL_SELECT_PAR_EMAIL );
        request.setParameter( PARAM_EMAIL, email );
        try {
            user = (User) request.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return user;
    }
    
    // Recherche d'un utilisateur à partir de son username et son mot de passe
    @Override
    public User LogIn(String username, String password) throws DAOException {
        User user = null;
        Query request = em.createQuery( JPQL_SELECT_USERNAME_PASSWORD );
        request.setParameter( PARAM_USERNAME, username );
        request.setParameter( PARAM_PASSWORD, password );
        try {
            user = (User) request.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return user;
    }
}