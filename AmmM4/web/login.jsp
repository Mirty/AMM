<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- 
        Pagina dedicata al login dell'utente: 
        1) test acquirente: email=acquirente@gmail.com; password=pass;
        2) test venditore:  email=venditore@gmail.com;  password=pass;
    -->
	<head>
		<title>Eataly - Login</title>
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
                        
                        <!-- Sezione di login -->
                        <div class="corpo_c_riga">
                            <div class="corpo_c_contenitore">
                                <div class="corpo_c_titolo">ACCEDI (per i clienti già  registrati)</div>
                                <div class="corpo_c_form">
                                    <!-- Inizio form -->
                                    <form action="Login" method="post">
                                        <div class="corpo_c_form_colonna">
                                            <div class="corpo_c_form_colonna_etichette">
                                                <label for="email">EMAIL :</label>
                                            </div>
                                            <div class="corpo_c_form_colonna_etichette">
                                                <label for="password">PASSWORD :</label>
                                            </div>
                                        </div>
                                        <div class="corpo_c_form_colonna">
                                            <div class="corpo_c_form_colonna_etichette">
                                                <input type="email" name="email" placeholder="mariorossi@gmail.com" id="email"/>
                                            </div>
                                            <div class="corpo_c_form_colonna_etichette">
                                                <input type="password" name="password" placeholder="password" id="password" />
                                            </div>
                                        </div>
                                        <div class="corpo_c_form_pulsante">
                                           <button type="submit" name="accedi"> LOGIN </button>
                                        </div>
                                        <div class="corpo_c_form_pulsante">
                                            <!-- 
                                                Introduco la var errore che verrà stampata nel caso in cui
                                                l'utente che sta cercando di loggarsi non è presente
                                                nella lista degli utenti registrati
                                            -->
                                            <p id="password_dimenticata">${errore}Hai dimenticato la password?</p>
                                        </div>
                                    </form>
                                    <!-- Fine form -->
                                </div>
                            </div>
                        </div>
                                        
                        <!-- Sezione di registrazione -->
                        <div class="corpo_c_riga">
                            <div class="corpo_c_contenitore">
                                <div class="corpo_c_titolo">REGISTRATI</div>
                                <div class="corpo_c_form">
                                    <!-- Inizio form -->
                                    <form action="registrazione.jsp" method="post">
                                        <div class="corpo_c_form_colonna">
                                            <div class="corpo_c_form_colonna_etichette">
                                                <label for="reg_email">EMAIL :</label>
                                            </div>
                                            <div class="corpo_c_form_colonna_etichette">
                                                <label for="reg_password">PASSWORD :</label>
                                            </div>
                                            <div class="corpo_c_form_colonna_etichette">
                                                <label for="re_reg_password">REINSERISCI PASSWORD :</label>
                                            </div>
                                        </div>
                                        <div class="corpo_c_form_colonna">
                                            <div class="corpo_c_form_colonna_etichette">
                                                <input type="email" name="reg_email" placeholder="mariorossi@gmail.com" id="reg_email"/>
                                            </div>
                                            <div class="corpo_c_form_colonna_etichette">
                                                <input type="password" name="reg_password" placeholder="password" id="reg_password" />
                                            </div>
                                            <div class="corpo_c_form_colonna_etichette">
                                                <input type="password" name="re_reg_password" placeholder="password" id="re_reg_password" />
                                            </div>
                                        </div>
                                        <div class="corpo_c_form_pulsante">
                                            <button type="submit" name="convalida"> SUBMIT </button>
                                        </div>
                                    </form>
                                    <!-- Fine form -->
                                </div>
                            </div>
                        </div>
                        
                        <div class="corpo_c_riga">
                            <div class="corpo_c_vantaggi">
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
                                        <a href="venditore.jsp" target="blank">questa</a> pagina. Che aspetti? Iscriviti subito su <a href="descrizione.jsp">Eataly.com</a> 
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
                        </div>
                    </div>
                </div>
                <!-- Fine corpo -->
                
                <!-- importo il footer -->
                <jsp:include page="footer.jsp" />
                
            </div>
	</body>
</html>