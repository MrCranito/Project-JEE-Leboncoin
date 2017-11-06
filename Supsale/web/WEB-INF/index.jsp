<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="supsale">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.min.js" ></script>
        <script type="text/javascript" src="http://cdn.jsdelivr.net/restangular/latest/restangular.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
    </head>
    <body>
               
        <c:choose>
        <c:when test="${ sessionScope.sessionUser == null }">
        <a href="inscription">Inscrivez-vous ! </a>
        <br /> <br />
        <a href="connexion">Connectez vous ! </a>
        <br />
        </c:when>
        <c:otherwise>
            <a href="inscription">Editez votre profil </a>
            <br /> <br />
            <a href="Logout">Déconnexion </a>
        </c:otherwise>      
        </c:choose>
        
        <h2>Bonjour <c:out value="${sessionScope.username}"></c:out> </h2>
        <form method="post" action="index">
            <c:if test="${ sessionScope.sessionUser != null }">
            <fieldset>
                <legend>Ajoutez une annonce</legend>
                
                <label for="name">Nom<span class="requis">*</span></label>
                <input type="text" name="name" value="<c:out value="${sale.name}"/>" size="20"/>
                <span class="erreur">${form.erreurs['name']}</span>
                <br />
                
                <label for="description">Description</label>
                <input type="textarea" name="description" value="<c:out value="${sale.description}"/>" size="20"/>
                <span class="erreur">${form.erreurs['description']}</span>
                <br />
                
                <label for="price">Prix</label>
                <input type="number" name="price" value="<c:out value="${sale.price}"/>" size="20"/>
                <span class="erreur">${form.erreurs['price']}</span>
                <br />
                
                <input type="submit" value="Ajouter" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
            </c:if>
        </form>
            <c:forEach items="${sales}" var='sale'>
            <tr>
                <td><c:out value="${sale.value.dateInscription}"/></td> <br />
                <td><c:out value="${sale.value.name}"/></td> <br />
                <td><c:out value="${sale.value.description}"/></td> <br />
                <td><c:out value="${sale.value.price}"/>€</td> <br />
                <c:if test="${ sessionScope.sessionUser != null }">
                <td>
                    <a href="<c:url value="/index"><c:param name="idSale" value="${ sale.key }"></c:param></c:url>">
                        Supprimer
                    </a> 
                </td>
                </c:if>
            </tr>
            <br /> <br />
            
            </c:forEach>
            
    </body>
</html>
