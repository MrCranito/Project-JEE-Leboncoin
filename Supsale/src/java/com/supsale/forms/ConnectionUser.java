package com.supsale.forms;

import com.supsale.dao.UserDao;
import com.supsale.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConnectionUser extends BaseRegister{
    
   
    private static final String SESSION_USER = "sessionUser";
    
    protected UserDao userDao;
     
    public ConnectionUser(UserDao userDao){
        this.userDao = userDao;
    }
    
    public User Connection(HttpServletRequest request){
        
        User user = new User();
        
        String username = getValeurChamp( request, USERNAME );
        String password = getValeurChamp( request, PASSWORD );

        HttpSession session = request.getSession();
        
        User login = userDao.LogIn(username, password);
        
        if (  login == null ){
            
            session.setAttribute(SESSION_USER, null);   
            setRegister(false);
            setResultat("Echec de la connexion.");     
        }
        else{
            user = userDao.find(login.getId());
               
            String email = user.getEmail();      
            String lastName = user.getLastName();
            String firstName = user.getFirstName();
            String phone = user.getPhoneNumber();
            String postal = user.getPostal();
        
            //création des variables de session
            session.setAttribute(ID, login.getId());
            session.setAttribute(SESSION_USER, user);
            session.setAttribute(LASTNAME, lastName);
            session.setAttribute(FIRSTNAME, firstName);
            session.setAttribute(EMAIL, email);
            session.setAttribute(PASSWORD, password);
            session.setAttribute(USERNAME, username);            
            session.setAttribute(PHONE, phone);
            session.setAttribute(POSTAL, postal);
            
            setRegister(true);
            setResultat("Succès de la connexion.");                      
        }
            return user;
    }
}
