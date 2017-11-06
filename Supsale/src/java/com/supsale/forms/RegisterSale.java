package com.supsale.forms;

import com.supsale.dao.DAOException;
import com.supsale.dao.SaleDao;
import com.supsale.entities.Sale;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

public class RegisterSale extends BaseRegister{
    
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    
    private SaleDao saleDao;
    
    public RegisterSale(SaleDao saleDao){
        this.saleDao = saleDao;
    }
    
    public Sale RegisterSale(HttpServletRequest request){
    
        String name = getValeurChamp(request, NAME);
        String description = getValeurChamp(request, DESCRIPTION);
        String price = getValeurChamp(request, PRICE);
        Timestamp date = new Timestamp( System.currentTimeMillis());
        
        Sale sale = new Sale();
        try {  
            CheckName(name, sale);
            sale.setDescription(description);
            sale.setPrice(price);
            sale.setDateInscription(date);

            if ( erreurs.isEmpty() ) {
                saleDao.create(sale);
                setResultat("L'annonce a été ajouté avec succès.");
            } else {
                setResultat("Échec de l'ajout de l'annonce.");
            }
        } catch ( DAOException e ) {
            setResultat("Échec de l'ajout de l'annonce : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");
        }
        
        return sale;
    }
    
    private void CheckName(String name, Sale sale){
        try {
            validationName( name );
        } catch ( FormValidationException e ) {
            setErreur( NAME, e.getMessage() );
        }
        sale.setName(name );
    }
    
    
    private void validationName(String name) throws FormValidationException{
        if ( name == null || name.length() < 3 ) {
            throw new FormValidationException( "Le nom de l'annonce doit contenir au moins 3 caractères." );
        }
    }
    
    /*private void validationPrice(String price) throws FormValidationException{
        if ( price != null ) {
            if ( !price.matches( "^[0-9]" ) ) {
                throw new FormValidationException( "Merci de saisir un prix valide" );        
        } else {
            throw new FormValidationException( "Merci de saisir un prix." );
        }
    }
    }*/

}
