package com.supsale.forms;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.supsale.entities.User;
import com.supsale.dao.DAOException;
import com.supsale.dao.UserDao;


public final class RegisterUser extends BaseRegister{
    
    //private static final String ALGO_CHIFFREMENT = "SHA-256";
 
    private UserDao userDao;
    

    public RegisterUser( UserDao userDao ) {
        this.userDao = userDao;
    }

    public User Register( HttpServletRequest request ) {
        String email = getValeurChamp( request, EMAIL );
        String password = getValeurChamp( request, PASSWORD );
        String confirmation = getValeurChamp( request, CONFIRMATION );
        String username = getValeurChamp( request, USERNAME );
        String lastName = getValeurChamp(request, LASTNAME);
        String firstName = getValeurChamp(request, FIRSTNAME);
        String phone = getValeurChamp(request, PHONE);
        String postal = getValeurChamp(request, POSTAL);
        
        User user = new User();
        try {
            CheckEmail( email, user );
            CheckPassword( password, confirmation, user );
            CheckUsername( username, user );
            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setPhoneNumber(phone);
            user.setPostal(postal);
            
            
            if ( erreurs.isEmpty() ) {
                userDao.create(user );
                setRegister(true);
                setResultat("Succès de l'inscription.");
            } else {
                setRegister(false);
                setResultat("Échec de l'inscription.");
            }
        } catch ( DAOException e ) {
            setResultat("Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.");         
        }

        return user;
    }

    private void CheckEmail( String email, User user ) {
        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
            setErreur( EMAIL, e.getMessage() );
        }
        user.setEmail( email );
    }

 
    private void CheckPassword( String password, String confirmation, User user ) {
        try {
            validationPassword( password, confirmation );
        } catch ( FormValidationException e ) {
            setErreur( PASSWORD, e.getMessage() );
            setErreur( CONFIRMATION, null );
        }

        //Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
       
        /*ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String encryptedPassword = passwordEncryptor.encryptPassword( password );*/

        user.setPassword(password );
    }

    
    private void CheckUsername( String username, User user ) {
        try {
            validationUsername( username );
        } catch ( FormValidationException e ) {
            setErreur( USERNAME, e.getMessage() );
        }
        user.setUsername(username );
    }

    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( userDao.findByEmail( email ) != null ) {
                throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }

    private void validationPassword( String password, String confirmation ) throws FormValidationException {
        if ( password != null && confirmation != null ) {
            if ( !password.equals( confirmation ) ) {
                throw new FormValidationException( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( password.length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    private void validationUsername( String username ) throws FormValidationException {
        if ( username == null || username.length() < 3 ) {
            throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    
}