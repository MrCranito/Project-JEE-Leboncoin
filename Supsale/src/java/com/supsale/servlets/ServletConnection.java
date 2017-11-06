package com.supsale.servlets;

import com.supsale.dao.UserDao;
import com.supsale.entities.User;
import com.supsale.forms.ConnectionUser;
import static com.supsale.servlets.ServletSale.VIEW;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/connexion"})
public class ServletConnection extends HttpServlet {

    public static final String VIEW = "/WEB-INF/connectionUser.jsp";
    
    @EJB
    private UserDao userDao;
    
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        
        this.getServletContext().getRequestDispatcher(VIEW).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       
        ConnectionUser form = new ConnectionUser(userDao);
        
        User user = form.Connection(request);
              
        request.setAttribute("form", form);
        request.setAttribute("user", user);
        
        if( form.isRegister()){
            response.sendRedirect( request.getContextPath() + "/index" );
        }
        else{
        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );  
        }
    }

}
