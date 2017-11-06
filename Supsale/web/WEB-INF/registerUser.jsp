<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
        <c:if test="${sessionScope.sessionUser != null }">
        <a href="index">Retourner à la page d'acceuil </a>
        <br />
        </c:if>
        <form method="post" action="inscription">
            <fieldset>
                <c:choose>
                <c:when test="${sessionScope.sessionUser == null }"><legend>Inscription</legend></c:when>    
                <c:otherwise>
                    <legend>Edition du profil</legend>
                </c:otherwise>
                </c:choose>
                
                <label for="email">Adresse email</label>
                <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>
                       <c:if test="${sessionScope.sessionUser != null }">
                           <c:out value="${sessionScope.email}"/>
                       </c:if>" size="20"/>
                <span class="erreur">${form.erreurs['email']}</span>
                <br />

                <label for="motdepasse">Mot de passe</label>
                <input type="password" id="motdepasse" name="password" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['password']}</span>
                <br />

                <c:if test="${sessionScope.sessionUser == null }">
                <label for="confirmation">Confirmation du mot de passe</label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
                <br />

                <label for="username">Nom d'utilisateur</label>
                <input type="text" id="nom" name="username" value="<c:out value="${user.username}"/>" size="20"/>
                <span class="erreur">${form.erreurs['username']}</span>
                <br />
                </c:if>
                  
                <label for="lastName">Nom</label>
                <input type="text" id="lastName" name="lastName" value="<c:out value="${user.lastName}"/>"
                       <c:if test="${sessionScope.sessionUser != null }">
                           <c:out value="${sessionScope.lastName}"/>
                       </c:if>/>
                <br />
                
                <label for="firstName">Prénom</label>
                <input type="text" name="firstName" value="<c:out value="${user.firstName}"/>
                       <c:if test="${sessionScope.sessionUser != null }">
                           <c:out value="${sessionScope.firstName}"/>
                       </c:if>" size="20"/>
                <br />
                
                <label for="phone">Téléphone</label>
                <input type="text" name="phone" value="<c:out value="${user.phoneNumber}"/>
                        <c:if test="${sessionScope.sessionUser != null }">
                           <c:out value="${sessionScope.phone}"/>
                       </c:if>" size="20" />
                <br />
                
                <label for="postal">Code postal</label>
                <input type="text" name="postal" value="<c:out value="${user.postal}"/>
                        <c:if test="${sessionScope.sessionUser != null }">
                           <c:out value="${sessionScope.postal}"/>
                       </c:if>" size="20"/>
                <br />
                <br />
                
                <c:choose>
                    <c:when test="${sessionScope.sessionUser == null }">
                        <input type="submit" value="Inscription" class="sansLabel" />
                    </c:when>
                    <c:otherwise><input type="submit" value="Appliquer" class="sansLabel" /></c:otherwise>
                </c:choose>
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
    </body>
</html>