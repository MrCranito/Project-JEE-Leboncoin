package com.supsale.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class BaseRegister {
    
    private String resultat;
    Map<String, String> erreurs = new HashMap<>();
    private boolean register; 

    protected static final String ID = "id";
    protected static final String EMAIL = "email";
    protected static final String PASSWORD = "password";
    protected static final String CONFIRMATION = "confirmation";
    protected static final String USERNAME = "username";
    protected static final String FIRSTNAME = "firstName";
    protected static final String LASTNAME = "lastName";
    protected static final String PHONE = "phone";
    protected static final String POSTAL = "postal";
    
    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }
    
    public  void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }
    
    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    public static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
