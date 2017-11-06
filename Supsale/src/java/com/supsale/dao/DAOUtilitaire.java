package com.supsale.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilitaire {

    
    private DAOUtilitaire() {
    }

    //close ResultSet
    public static void Close( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "ResultSet failed to close : " + e.getMessage() );
            }
        }
    }

    //close Statement 
    public static void Close( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Statement failed to close : " + e.getMessage() );
            }
        }
    }

    //close connexion
    public static void Close( Connection connexion ) {
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Connexion failed to close : " + e.getMessage() );
            }
        }
    }

    //close Connexion and Statement
    public static void Close( Statement statement, Connection connexion ) {
        Close( statement );
        Close( connexion );
    }

    //close ResultSet, Connexion and Statement
    public static void Close( ResultSet resultSet, Statement statement, Connection connexion ) {
        Close( resultSet );
        Close( statement );
        Close( connexion );
    }

    //init prepared request
    public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement( sql );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
}