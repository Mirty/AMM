<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--
        In questa pagina dedicata, come intuibile dal titolo, all'acquirente,
        rendo visibile la tabella contenente tutti gli oggetti che sono stati
        messi in vendita, e che il cliente può acquistare
    -->
    <head>
        <title>Eataly - Cliente</title>
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
                    <!-- Inizio tabella -->
                    <table>
                        <tr>
                            <th>Nome</th>
                            <th>Immagine</th>
                            <th>In stock</th>
                            <th>Prezzo (in euro)</th>
                            <th>Descrizione</th>
                            <th>Aggiungi al carrello</th>
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
                                <td>${oggetto.getNome()}</td>
                                <td>
                                    <img src="${oggetto.getUrlImg()}" alt="${oggetto.getNome()}" title="${oggetto.getNome()}" height="90">
                                </td>
                                <td>${oggetto.getInStock()}</td>
                                <td>${oggetto.getPrezzo()}</td>
                                <td class="descr_table">${oggetto.getDescrizione()}</td>
                                <td>
                                    <!-- Quando clicco sul carrello, mi rimanda alla servlet Acquirente, passandomi come id l'id del prodotto selezionato -->
                                    <a href="Acquirente?productId=${oggetto.id}"><img src="imgs/header/carrello.png" alt="Aggiungi al carrello" height="40"></a>
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