package com.supsale.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supsale.entities.User;
import com.supsale.dao.UserDao;
import com.supsale.forms.BaseRegister;
import com.supsale.forms.RegisterUser;
import javax.ejb.EJB;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/inscription"})
public class ServletUser extends HttpServlet {

    public static final String VIEW = "/WEB-INF/registerUser.jsp";
    
    @EJB
    private UserDao userDao;

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
 
        this.getServletContext().getRequestDispatcher(VIEW).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       
            RegisterUser form = new RegisterUser( userDao );          
            //Traitement de la requête et récupération du entities en résultant
            User user = form.Register(request);
            
            // Stockage du formulaire et du bean dans l'objet request
            request.setAttribute( "form", form );
            request.setAttribute( "user", user );
            
            if(form.isRegister()){
                response.sendRedirect( request.getContextPath() + "/connexion" );
            }
            else{
            this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
            }
    }
}