<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--
        Da questa pagina è possibile visualizzare il riepilogo di un oggetto.
        In particolare, dato che, come suggerito, 
        "Il riepilogo delle caratteristiche di un oggetto da acquistare 
        è molto simile al riepilogo dell’oggetto aggiunto nella lista" (dal venditore),
        uso questa pagina sia per visualizzare la conferma dell'oggetto appena creato
        dal venditore, e sia per il riepilogo dell'oggetto che l'utente ha intenzione di acquistare
        o semplicemente visualizzare. Infatti vengo reindirizzata a questa pagina da due servlet:
        Acquirente e Venditore. 
    -->
    <head>
        <title>Eataly - ${oggetto.nome}</title>
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
                    <div id="corpo_c_visualizza">
                        <div id="corpo_c_visualizza_img"><img src="imgs/obj/${oggetto.getUrlImg()}" width="150" alt="url img"></div>
                        <div id="corpo_c_visualizza_info">
                            <div id="corpo_c_visualizza_info_nome">${oggetto.getNome()}</div>
                            <div class="corpo_c_visualizza_info_generiche">Marca: ${oggetto.getMarca()}</div>
                            <div class="corpo_c_visualizza_info_generiche">Disponibilità: ${oggetto.getInStock()} pezzi</div>
                            <div class="corpo_c_visualizza_info_generiche">Prezzo: ${oggetto.getPrezzo()} €</div>
                            <div class="corpo_c_visualizza_info_generiche">Descrizione: ${oggetto.getDescrizione()}</div>
                            <!-- Mostro il carrello solo se si è loggato l'acquirente -->
                            ${carrello}
                        </div>
                        <!-- Mostro il pulsante solo se si è loggato il venditore -->
                        <c:if test="${venditoreLoggedIn==true}"> 
                            <div class="corpo_c_form_pulsante">
                                   <button type="submit" name="convalida"> INSERISCI </button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <!-- Fine corpo -->

            <!-- importo il footer -->
            <jsp:include page="footer.jsp" />

        </div>
    </body>
</html>