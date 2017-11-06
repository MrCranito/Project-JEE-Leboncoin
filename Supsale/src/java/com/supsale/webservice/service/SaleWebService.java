package com.supsale.webservice.service;

import com.supsale.dao.SaleDao;
import com.supsale.dao.SaleDaoImpl;
import com.supsale.entities.Sale;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/listSale")
public class SaleWebService {
    
    @EJB
    private SaleDao saleDao;
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sale> getAllSales(){
     
        
        List<Sale> saleD = new ArrayList<Sale>();
        
        saleD = saleDao.list();
        
        return saleD;
    }
}
