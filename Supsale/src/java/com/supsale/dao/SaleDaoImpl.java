package com.supsale.dao;

import com.supsale.entities.Sale;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class SaleDaoImpl implements SaleDao{

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "SupsalePU" )
    private EntityManager em;
    
    // Enregistrement d'une nouvelle annonce
    @Override
    public void create(Sale sale) throws DAOException {
        try {
            em.persist( sale );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    @Override
    public Sale find(String email) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sale> list() {
        try{
            TypedQuery<Sale> query = em.createQuery("SELECT s FROM Sale s ORDER BY s.price DESC", Sale.class);
            return query.getResultList();
        }catch(Exception e){
            throw new DAOException(e);
        }
    }
    
    @Override
    public void Delete( Sale sale ) throws DAOException {
        try {
            em.remove( em.merge( sale ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}
