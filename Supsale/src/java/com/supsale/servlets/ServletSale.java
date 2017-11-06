package com.supsale.servlets;

import com.supsale.dao.DAOException;
import com.supsale.dao.SaleDao;
import com.supsale.entities.Sale;
import com.supsale.forms.BaseRegister;

import com.supsale.forms.RegisterSale;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@WebServlet(urlPatterns = {"/index"})
    public class ServletSale extends HttpServlet{ 
    public static final String VIEW = "/WEB-INF/index.jsp";
    public static final String PARAM_ID_SALE = "idSale";
    public static final String SESSION_SALE = "sale";
    
    @EJB
    private SaleDao saleDao;

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
       /* BufferReader buff = ClientBuilder.newClient();
        String name = client.target("http://localhost:8080/Supsale/webresources/listSale")
        .request(MediaType.APPLICATION_JSON)
        .get(String.class);
        */
       
       //JSONObject obj = buff;
        
        List<Sale> salesList = saleDao.list();
        Map<Long, Sale> mapSales = new HashMap<Long, Sale>();
        for(Sale sale : salesList){
            mapSales.put(sale.getId(), sale);
        }
        request.setAttribute("sales", mapSales);
                
        //Récupération paramètre
        String idSale = BaseRegister.getValeurChamp(request, PARAM_ID_SALE);
        if(idSale != null){
        
        Long id = Long.parseLong(idSale);
        
        //Récupération de la Map des commandes enregistrées en session

        //Si l'id de la commande et la Map des commandes ne sont pas vides
        if ( id != null && mapSales != null ) {
            try {
                //Alors suppression de la commande de la BDD
                saleDao.Delete(mapSales.get( id ) );
                //Puis suppression de la commande de la Map
                mapSales.remove( id );
            } catch ( DAOException e ) {
                e.printStackTrace();
            }    
        }
        } 
        this.getServletContext().getRequestDispatcher(VIEW).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       
            RegisterSale form = new RegisterSale(saleDao);          
            Sale sale = form.RegisterSale(request);
            
            request.setAttribute("form", form);
            request.setAttribute("sale", sale);

            
            doGet(request, response);
            
            //this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );  
    }
    
}