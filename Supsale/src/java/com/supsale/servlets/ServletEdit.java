package com.supsale.servlets;

import com.supsale.dao.UserDao;
import com.supsale.forms.EditProfilUser;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/edit"})
public class ServletEdit extends HttpServlet {

    public static final String VIEW = "/WEB-INF/registerUser.jsp";
    
    @EJB
    private UserDao userDao;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        EditProfilUser form = new EditProfilUser( userDao ); 
        
        form.EditProfil(request);
        
        request.setAttribute( "form", form );
        
        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
    }

}
