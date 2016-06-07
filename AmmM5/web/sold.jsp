<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--
        Da questa pagina è possibile visualizzare gli oggetti che il venditore
        ha messo in vendita
    -->
    <head>
        <title>Eataly - Oggetti messi in vendita</title>
        <meta charset="utf-8"/>
        <meta name="author" content="Marta Murtas">
        <meta name="description" content="Milestone 2 per il corso di AMM">
        <meta name="keywords" content="ecommerce, AMM">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>

    <body>
        <!-- importo l'header -->
        <jsp:include page="header.jsp" />


        <div id="wrapper">

            <!-- importo la sidebar -->
            <jsp:include page="sidebar.jsp" />

            <!-- Inizio corpo -->
            <div id="corpo">
                <div id="corpo_c">
                    <div class="corpo_c_vantaggi">
                        <p>Questi sono gli oggetti che hai inserito su <a href="descrizione.jsp">Eataly.com</a>.</p>
                        <p><a href="venditore.jsp">Clicca quì per inserire un nuovo oggetto</a></p>
                    </div>
                    <!-- Inizio tabella -->
                    <table>
                        <tr>
                            <th>Nome</th>
                            <th>Immagine</th>
                            <th>In stock</th>
                            <th>Prezzo (€)</th>
                            <th>Peso</th>
                            <th>Descrizione</th>
                            <th>Venduti</th>
                            <th>Modifica</th>
                            <th>Elimina</th>
                        </tr>
                        <!-- uso un contatore per contare le righe -->
                        <c:set var="riga" value="0" scope="page" />
                        <c:forEach var="oggetto" items="${oggetti}">
                            <!-- Verifico se la riga della tabella è pari o dispari -->
                            <c:choose>
                                <c:when test="${riga%2==0}" >
                                    <tr class="riga_pari">
                                </c:when>
                                <c:otherwise>
                                    <tr class="riga_dispari">
                                </c:otherwise>
                            </c:choose>
                                <td>
                                    <a href="Venditore?productId=${oggetto.id}">${oggetto.getNome()}</a></td>
                                <td>
                                    <img src="imgs/obj/${oggetto.getUrlImg()}" alt="${oggetto.getNome()}" title="${oggetto.getNome()}" height="90">
                                </td>
                                <td>${oggetto.getInStock()}</td>
                                <td>${oggetto.getPrezzo()}</td>
                                <td>${oggetto.getPeso()}</td>
                                <td class="descr_table">${oggetto.getDescrizione()}</td>
                                <td>${oggetto.getVenduti()}</td>
                                <td>
                                    <a href="Venditore?EditProductId=${oggetto.id}"><img src="imgs/edit.png" alt="Modifica oggetto" height="30"></a>
                                </td>
                                <td>
                                    <a href="Venditore?DelProductId=${oggetto.id}"><img src="imgs/delete.png" alt="Elimina oggetto" height="30"></a>
                                </td>
                            </tr>
                            <c:set var="riga" value="${riga+1}" scope="page"/>
                        </c:forEach>
                    </table>
                    <!-- Fine tabella -->
                </div>
            </div>
            <!-- Fine corpo -->

            <!-- importo il footer -->
            <jsp:include page="footer.jsp" />

        </div>
    </body>
</html>