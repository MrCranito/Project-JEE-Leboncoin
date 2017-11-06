package com.supsale.dao;

import com.supsale.entities.Sale;
import java.util.List;


public interface SaleDao {
    
    void create( Sale sale ) throws DAOException;

    List<Sale> list() throws DAOException;
    
    Sale find( String email ) throws DAOException;
    
    void Delete( Sale sale ) throws DAOException;
}