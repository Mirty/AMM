<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--
        Da questa pagina verifico se l'acquisto è andato a buon fine o meno a seconda
        che il cliente abbia o meno abbastanza soldi nel conto per procedere 
        con l'acquisto del prodotto selezionato.
        Il controllo lo effettuo nella servlet Acquirente
    -->
    <head>
        <title>Eataly - Verifica acquisto</title>
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
                    <div class="corpo_c_riga">
                        <div class="corpo_c_contenitore" id="sommario">
                            <div class="corpo_c_titolo"><h1>Ricapitolando...</h1></div>
                            <div class="corpo_c_form">
                                ${messaggio}
                            </div>
                        </div>	
                    </div>
                </div>
            </div>
            <!-- Fine corpo -->
            
            <!-- importo il footer -->
            <jsp:include page="footer.jsp" />
        </div>
    </body>
</html>
