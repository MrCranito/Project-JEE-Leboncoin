package com.supsale.dao;

import com.supsale.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.supsale.dao.DAOUtilitaire.*;

public class UserDaoImpl_obsolete {

    private DAOFactory daoFactory;
    private static final String SQL_INSERT = "INSERT INTO user (email, password, username, lastName, firstName, phone, postal) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM user WHERE email = ?";

    UserDaoImpl_obsolete(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public User find(String email) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connexion = daoFactory.getConnection();
             preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_PAR_EMAIL, email);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            Close(resultSet, preparedStatement, connexion);
        }
        return user;
    }

    public void create(User user) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            //Récupération de la méthode Connexion depuis DAOFactory
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, user.getEmail(), user.getPassword(), user.getUsername(), user.getLastName(), user.getFirstName(), user.getPhoneNumber(), user.getPostal());
            int statut = preparedStatement.executeUpdate();
            //si le statut de la requete vaut 0 une DAOException est retournée
            if (statut == 0) {
                throw new DAOException("Failed to create user, no rows added in table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            Close(preparedStatement, connexion);
        }
    }

//mapping entre les colonnes en db et le bean User
    private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setUsername(resultSet.getString("username"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setPostal(resultSet.getString("postal"));
        user.setPhoneNumber(resultSet.getString("phone"));
        return user;
    }
}
