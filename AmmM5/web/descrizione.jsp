<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Questa è la pagina iniziale: breve presentazione di Eataly.com  -->
    <head>
            <title>Eataly - Descrizione</title>
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
                        <!-- Sommario iniziale -->
                        <div class="corpo_c_contenitore" id="sommario">
                            <div class="corpo_c_titolo"><h1>Sommario</h1></div>
                            <div class="corpo_c_form">
                                <a href="#prodotti_venduti"><h3>Prodotti venduti</h3></a>
                                <a href="#servizi_offerti"><h3>Servizi offerti</h3></a>
                            </div>
                        </div>
                        <!-- Fine sommario -->	

                        <!-- Inizio parte prodotti venduti -->
                        <div class="corpo_c_vantaggi"><a id="prodotti_venduti"></a>
                            <h2>Prodotti venduti</h2>
                            <h3><a href="descrizione.jsp">Eataly.com</a> ti offre diversi vantaggi!<br>Ti diamo la possibilità  di acquistare</h3>
                            <ul>
                                <li> 
                                    <h4>PASTA</h4>
                                    Rivendiamo le migliori marche di pasta: Barilla, De Cecco, Voiello, Del Verde, ...
                                </li>
                                <li> 
                                    <h4>FORMAGGI</h4>
                                    Potrai trovare Grana Padano, Asiago, Fontina, Robiola Osella, ...
                                </li>
                                <li> 
                                    <h4>SALUMI</h4>
                                    Dal prosciutto crudo San Daniele, di Parma, ... al prosciutto cotto ... 
                                </li>
                                <li> 
                                    <h4>VINI</h4>

                                </li>
                                <li> 
                                    <h4>LIQUORI</h4>
                                </li>
                            </ul>
                        </div>
                        <!-- Fine parte prodotti venduti -->

                        <!-- Inizio parte servizi offerti -->
                        <div class="corpo_c_vantaggi"><a id="servizi_offerti"></a>
                            <h3>Registrandoti otterrai diversi vantaggi!<br>Ti offriamo la possibilità  di diventare:</h3>
                            <ul>
                                <li> 
                                    <h4>Un <a href="venditore.jsp">VENDITORE</a></h4>
                                    Sei un rivenditore di prodotti tipici usati dalle famiglie e dalle gastronomie italiane? Affrettati ad iscriverti su 
                                    <a href="descrizione.jsp">Eataly.com</a> per mettere il tuo prodotto subito in commercio rendendolo alla portata 
                                    degli acquirenti di tutto il mondo, aumentando a dismisura gli introiti della tua azienda! 
                                    Registrarsi è completamente gratuito e lo stesso vale per la messa in vendita dei tuoi beni!
                                    Per l'inserimento dei tuoi prodotti non avrai bisogno di un tecnico! La nostra interfaccia user-friendly renderà  il procedimento
                                    gradevole e molto veloce. Dopo aver effettuato la registrazione potrai mettere in commercio i tuoi prodotti da 
                                    <a href="Login" target="blank">questa</a> pagina. Che aspetti? Iscriviti subito su <a href="descrizione.jsp">Eataly.com</a> 
                                </li>
                                <li> 
                                    <h4>Un <a href="cliente.jsp">CLIENTE</a></h4>
                                    Sei stato in vacanza in Italia e hai fatto esperienza della nostra arte culinaria? Sei rimasto coinvolto emotivamente 
                                    nell'assaggio delle nostre prelibatezze? Le tue papille gustative ti hanno spedito dritto al paradiso provocandoti 
                                    un'apoteosi del gusto? O sei semplicemente un italiano all'estero che non riesce a fare a meno del cibo della sua 
                                    madrepatria? (disclaimer: non illuderti, la cucina della nonna sarà  sempre e comunque irraggiungibile).
                                    <a href="descrizione.jsp">Eataly.com</a> ti permette di acquistare a prezzi super convenienti i prodotti
                                    che potresti trovare entrando in ogni bottega italiana, portando i tuoi prodotti preferiti a casa tua in pochissimi
                                    giorni lavorativi! Rivendiamo le marche di nicchia come quelle più conosciute ed usate nella cucina di un tipico
                                    italiano. Grazie a <a href="descrizione.jsp">Eataly.com</a> potrai finalmente sentirti come Marcel Proust con la sua madeleine
                                    e tornerai indietro nel tempo nei tuoi ricordi italiani più felici, semplicemente mangiando i tuoi cibi preferiti comodamente da casa tua!
                                </li>
                            </ul>
                        </div>
                        <!-- Fine parte servizi offerti -->
                    </div>
                </div>
            </div>
            <!-- Fine corpo -->

            <!-- importo il footer -->
            <jsp:include page="footer.jsp" />

        </div>
    </body>
</html>