package com.supsale.dao;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String propertiesFile = "/com/supsale/dao/dao.properties";
    BoneCP connectionPool = null;

    
   DAOFactory( BoneCP connectionPool ) {
        this.connectionPool = connectionPool;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;
        BoneCP connectionPool = null;
        
        //chargement du fichier dao.properties 
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( propertiesFile );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "The properties file " + propertiesFile + " can not be found." );
        }

        try {
            //chargement des propriétés du fichier dao.properties
            properties.load( fichierProperties );
            url = properties.getProperty( "url" );
            driver = properties.getProperty( "driver" );
            nomUtilisateur = properties.getProperty( "username" );
            motDePasse = properties.getProperty( "password" );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Failed to load properties file " + propertiesFile, e );
        }

        try {
            //chargement du driver 
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "The driver can not be found in the classpath.", e );
        }

        try {
            /*
             * Création d'une configuration de pool de connexions via l'objet
             * BoneCPConfig et les différents setters associés.
             */
            BoneCPConfig config = new BoneCPConfig();
            /* Mise en place de l'URL, du nom et du mot de passe */
            config.setJdbcUrl( url );
            config.setUsername( nomUtilisateur );
            config.setPassword( motDePasse );
            /* Paramétrage de la taille du pool */
            config.setMinConnectionsPerPartition( 5 );
            config.setMaxConnectionsPerPartition( 10 );
            config.setPartitionCount( 2 );
            /* Création du pool à partir de la configuration, via l'objet BoneCP */
            connectionPool = new BoneCP( config );
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DAOConfigurationException( "Erreur de configuration du pool de connexions.", e );
        }
        /*
         * Enregistrement du pool créé dans une variable d'instance via un appel
         * au constructeur de DAOFactory
         */
        DAOFactory instance = new DAOFactory(connectionPool);
        return instance;
    }
        //Etablissement de la connexion à la base de donnée
        Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    
    /*public UserDao getUtilisateurDao() {
        return new UserDaoImpl( this );
    }*/
}